package object kmedianas2D {
  import scala.annotation.tailrec
  import scala.collection.{Map, Seq}
  import scala.collection.parallel.CollectionConverters._
  import scala.util.Random
  import common._

  // Definición de clase Punto
  class Punto(val x: Double, val y: Double) {
    private def cuadrado(v: Double): Double = v * v
    def distanciaAlCuadrado(that: Punto): Double =
      cuadrado(that.x - x) + cuadrado(that.y - y)
    private def round(v: Double): Double = (v * 100).toInt / 100.0
    override def toString = s"(${round(x)}, ${round(y)})"
  }

  // Generación de puntos aleatorios
  def generarPuntos(k: Int, num: Int): Seq[Punto] = {
    val randx = new Random(1)
    val randy = new Random(3)
    (0 until num).map { i =>
      val x = ((i + 1) % k) * 1.0 / k + randx.nextDouble() * 0.5
      val y = ((i + 5) % k) * 1.0 / k + randy.nextDouble() * 0.5
      new Punto(x, y)
    }
  }

  // Inicialización de medianas
  def inicializarMedianas(k: Int, puntos: Seq[Punto]): Seq[Punto] = {
    val rand = new Random(7)
    (0 until k).map(_ => puntos(rand.nextInt(puntos.length)))
  }

  // Función para hallar el punto más cercano
  def hallarPuntoMasCercano(p: Punto, medianas: Seq[Punto]): Punto = {
    assert(medianas.nonEmpty)
    medianas.minBy(p.distanciaAlCuadrado)
  }

  // Función de clasificación secuencial
  def clasificarSeq(puntos: Seq[Punto], medianas: Seq[Punto]): Map[Punto, Seq[Punto]] = {
    puntos.groupBy(hallarPuntoMasCercano(_, medianas))
  }

  // Función de clasificación paralela
  def clasificarPar(umb: Int)(puntos: Seq[Punto], medianas: Seq[Punto]): Map[Punto, Seq[Punto]] = {
    if (puntos.size <= umb) clasificarSeq(puntos, medianas)
    else {
      val clasificadoPar = puntos.par.map(punto => (hallarPuntoMasCercano(punto, medianas), punto))
      clasificadoPar.groupBy(_._1).map { case (mediana, puntosCluster) =>
        (mediana, puntosCluster.map(_._2).seq)  // Convertir a colección secuencial
      }.seq // Convertir a Map secuencial
    }
  }


  // Función de cálculo de promedio secuencial
  def calculePromedioSeq(medianaVieja: Punto, puntos: Seq[Punto]): Punto = {
    if (puntos.isEmpty) medianaVieja
    else new Punto(puntos.map(_.x).sum / puntos.size, puntos.map(_.y).sum / puntos.size)
  }

  // Función de cálculo de promedio paralelo
  def calculePromedioPar(medianaVieja: Punto, puntos: Seq[Punto]): Punto = {
    if (puntos.isEmpty) medianaVieja
    else {
      val puntosPar = puntos.par
      new Punto(puntosPar.map(_.x).sum / puntos.size, puntosPar.map(_.y).sum / puntos.size)
    }
  }

  // Función de actualización secuencial
  def actualizarSeq(clasif: Map[Punto, Seq[Punto]], medianasViejas: Seq[Punto]): Seq[Punto] = {
    medianasViejas.map(mediana => calculePromedioSeq(mediana, clasif.getOrElse(mediana, Seq())))
  }

  // Función de actualización paralela
  def actualizarPar(clasif: Map[Punto, Seq[Punto]], medianasViejas: Seq[Punto]): Seq[Punto] = {
    medianasViejas.par.map(mediana => calculePromedioPar(mediana, clasif.getOrElse(mediana, Seq()))).seq
  }

  // Función de convergencia secuencial
  def hayConvergenciaSeq(eta: Double, medianasViejas: Seq[Punto], medianasNuevas: Seq[Punto]): Boolean = {
    medianasViejas.zip(medianasNuevas).forall {
      case (vieja, nueva) => vieja.distanciaAlCuadrado(nueva) <= eta
    }
  }
  // Función de convergencia paralela
  def hayConvergenciaPar(eta: Double, medianasViejas: Seq[Punto], medianasNuevas: Seq[Punto]): Boolean = {
    medianasViejas.par.zip(medianasNuevas.par).forall {
      case (vieja, nueva) => vieja.distanciaAlCuadrado(nueva) <= eta
    }
  }

  // Implementación del algoritmo kmeans secuencial
  @tailrec
  final def kMedianasSeq(puntos: Seq[Punto], medianas: Seq[Punto], eta: Double): Seq[Punto] = {
    val clasif = clasificarSeq(puntos, medianas)
    val medianasNuevas = actualizarSeq(clasif, medianas)
    if (hayConvergenciaSeq(eta, medianas, medianasNuevas)) medianasNuevas
    else kMedianasSeq(puntos, medianasNuevas, eta)
  }

  // Implementación del algoritmo kmeans paralelo
  @tailrec
  final def kMedianasPar(puntos: Seq[Punto], medianas: Seq[Punto], eta: Double): Seq[Punto] = {
    val clasif = clasificarPar(100)(puntos, medianas) // 100 es un umbral arbitrario
    val medianasNuevas = actualizarPar(clasif, medianas)
    if (hayConvergenciaPar(eta, medianas, medianasNuevas)) medianasNuevas
    else kMedianasPar(puntos, medianasNuevas, eta)
  }
}
