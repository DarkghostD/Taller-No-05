import Benchmark._
import kmedianas2D._

// Prueba 1: Generando 16 puntos para encontrar 3 clusters
val puntos16_3 = generarPuntos(3, 16).toSeq
// Midiendo tiempos para secuencial y paralelo
val tiempos1 = tiemposKmedianas(puntos16_3, 3, 0.01)
// Visualización de clusters
//probarKmedianas(puntos16_3, 3, 0.01)
println(s"Tiempos para 16 puntos y 3 clusters: $tiempos1")

// Prueba 2: Generando 128 puntos para encontrar 4 clusters
val puntos128_4 = generarPuntos(4, 128).toSeq
val tiempos2 = tiemposKmedianas(puntos128_4, 4, 0.01)
//probarKmedianas(puntos128_4, 4, 0.01)
println(s"Tiempos para 128 puntos y 4 clusters: $tiempos2")

// Prueba 3: Generando 512 puntos para encontrar 8 clusters
val puntos512_8 = generarPuntos(8, 512).toSeq
val tiempos3 = tiemposKmedianas(puntos512_8, 8, 0.01)
//probarKmedianas(puntos512_8, 8, 0.01)
println(s"Tiempos para 512 puntos y 8 clusters: $tiempos3")

// Prueba 4: Generando 1024 puntos para encontrar 16 clusters
val puntos1024_16 = generarPuntos(16, 1024).toSeq
val tiempos4 = tiemposKmedianas(puntos1024_16, 16, 0.01)
//probarKmedianas(puntos1024_16, 16, 0.01)
println(s"Tiempos para 1024 puntos y 16 clusters: $tiempos4")

// Prueba 5: Generando 4096 puntos para encontrar 32 clusters con un eta más estricto
val puntos4096_32 = generarPuntos(32, 4096).toSeq
val tiempos5 = tiemposKmedianas(puntos4096_32, 32, 0.001)
//probarKmedianas(puntos4096_32, 32, 0.001)
println(s"Tiempos para 4096 puntos y 32 clusters: $tiempos5")

// Prueba 6: Generando 16384 puntos para encontrar 64 clusters con un eta más estricto
val puntos16384_64 = generarPuntos(64, 16384).toSeq
val tiempos6 = tiemposKmedianas(puntos16384_64, 64, 0.001)
//probarKmedianas(puntos16384_64, 64, 0.001)
println(s"Tiempos para 16384 puntos y 64 clusters: $tiempos6")

// Prueba 7: Generando 32768 puntos para encontrar 128 clusters
val puntos32768_128 = generarPuntos(128, 32768).toSeq
val tiempos7 = tiemposKmedianas(puntos32768_128, 128, 0.001)
//probarKmedianas(puntos32768_128, 128, 0.001)
println(s"Tiempos para 32768 puntos y 128 clusters: $tiempos7")

// Prueba 8: Generando 65536 puntos para encontrar 256 clusters (alta carga)
val puntos65536_256 = generarPuntos(256, 65536).toSeq
val tiempos8 = tiemposKmedianas(puntos65536_256, 256, 0.001)
//probarKmedianas(puntos65536_256, 256, 0.001)
println(s"Tiempos para 65536 puntos y 256 clusters: $tiempos8")
