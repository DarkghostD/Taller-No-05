import Benchmark._
import kmedianas2D._

// Generando 4096 puntos para encontrar 64 clusters
val puntos4096_64 = generarPuntos(64, 4096).toSeq
tiemposKmedianas(puntos4096_64, 64, 0.01)
probarKmedianas(puntos4096_64, 64, 0.01)

// Generando 8192 puntos para encontrar 128 clusters
val puntos8192_128 = generarPuntos(128, 8192).toSeq
// Midiendo tiempos
tiemposKmedianas(puntos8192_128, 128, 0.01)
// Visualizando Clusters
probarKmedianas(puntos8192_128, 128, 0.01)

// Generando 16384 puntos para encontrar 256 clusters
val puntos16384_256 = generarPuntos(256, 16384).toSeq
// Midiendo tiempos
tiemposKmedianas(puntos16384_256, 256, 0.01)
// Visualizando Clusters
probarKmedianas(puntos16384_256, 256, 0.01)

// Generando 32768 puntos para encontrar 512 clusters
val puntos32768_512 = generarPuntos(512, 32768).toSeq
// Midiendo tiempos
tiemposKmedianas(puntos32768_512, 512, 0.01)
// Visualizando Clusters
probarKmedianas(puntos32768_512, 512, 0.01)

// Prueba 1: Generando 16 puntos para encontrar 3 clusters
val puntos8192_32 = generarPuntos(32, 8192).toSeq
// Midiendo tiempos para secuencial y paralelo
val tiempos1 = tiemposKmedianas(puntos8192_32, 3, 0.01)
// Visualización de clusters
probarKmedianas(puntos8192_32, 32, 0.01)
println(s"Tiempos para 8192 puntos y 32 clusters: $tiempos1")

// Prueba 2: Generando 8192_64 puntos para encontrar 3 clusters
val puntos8192_64 = generarPuntos(64, 8192).toSeq
// Midiendo tiempos para secuencial y paralelo
val tiempos1 = tiemposKmedianas(puntos8192_32, 3, 0.01)
// Visualización de clusters
probarKmedianas(puntos8192_32, 32, 0.01)
println(s"Tiempos para 8192 puntos y 32 clusters: $tiempos1")

// Prueba 6: Generando 16384 puntos para encontrar 64 clusters con un eta más estricto
val puntos16384_64 = generarPuntos(64, 16384).toSeq
val tiempos6 = tiemposKmedianas(puntos16384_64, 64, 0.001)
probarKmedianas(puntos16384_64, 64, 0.001)
println(s"Tiempos para 16384 puntos y 64 clusters: $tiempos6")

// Prueba 7: Generando 32768 puntos para encontrar 128 clusters
val puntos32768_128 = generarPuntos(128, 32768).toSeq
val tiempos7 = tiemposKmedianas(puntos32768_128, 128, 0.001)
probarKmedianas(puntos32768_128, 128, 0.001)
println(s"Tiempos para 32768 puntos y 128 clusters: $tiempos7")

// Prueba 8: Generando 65536 puntos para encontrar 256 clusters (alta carga)
val puntos65536_256 = generarPuntos(256, 65536).toSeq
val tiempos8 = tiemposKmedianas(puntos65536_256, 256, 0.001)
probarKmedianas(puntos65536_256, 256, 0.001)
println(s"Tiempos para 65536 puntos y 256 clusters: $tiempos8")
