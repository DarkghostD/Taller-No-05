package Benchmark

import kmedianas2D._
import scala.concurrent.duration._
import plotly._
import plotly.element._
import plotly.layout._
import plotly.Plotly

object Benchmark {

  // Función para medir el tiempo de ejecución y aceleración de ambas versiones
  def tiemposKmedianas(puntos: Seq[Punto], k: Int, eta: Double): (Double, Double, Double) = {
    val startSeq = System.nanoTime()
    kMedianasSeq(puntos, inicializarMedianas(k, puntos), eta)
    val endSeq = System.nanoTime()
    val tiempoSecuencial = (endSeq - startSeq).nanos.toMillis

    val startPar = System.nanoTime()
    kMedianasPar(puntos, inicializarMedianas(k, puntos), eta)
    val endPar = System.nanoTime()
    val tiempoParalelo = (endPar - startPar).nanos.toMillis

    val aceleracion = tiempoSecuencial.toDouble / tiempoParalelo.toDouble

    (tiempoSecuencial, tiempoParalelo, aceleracion)
  }

  // Función para probar k-means y generar gráficos de los resultados
  def probarKmedianas(puntos: Seq[Punto], k: Int, eta: Double): (Double, Double, Double) = {
    val (tiempoSeq, tiempoPar, aceleracion) = tiemposKmedianas(puntos, k, eta)

    // Ejecutar k-means en ambas versiones para obtener los clusters
    val clustersSeq = kMedianasSeq(puntos, inicializarMedianas(k, puntos), eta)
    val clustersPar = kMedianasPar(puntos, inicializarMedianas(k, puntos), eta)

    // Crear gráficos para la versión secuencial
    val secuencialFigura = createGraph(clustersSeq, "K-Medias Secuencial")
    secuencialFigura.plot("secuencial.html")

    // Crear gráficos para la versión paralela
    val paraleloFigura = createGraph(clustersPar, "K-Medias Paralelo")
    paraleloFigura.plot("paralelo.html")

    (tiempoSeq, tiempoPar, aceleracion)
  }

  // Función auxiliar para crear gráficos de clusters con Plotly
  private def createGraph(clusters: Seq[Punto], titulo: String): Figure = {
    val puntosX = clusters.map(_.x)
    val puntosY = clusters.map(_.y)

    val scatter = Scatter(
      x = puntosX,
      y = puntosY,
      mode = ScatterMode(ScatterMode.Markers),
      marker = Marker(color = Color.RGBA(255, 0, 0, 0.8))
    )

    Figure(
      data = Seq(scatter),
      layout = Layout(title = titulo)
    )
  }
}
