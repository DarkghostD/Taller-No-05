import Benchmark._
import kmedianas2D._

val puntos16_3 = generarPuntos(3, 16).toSeq
tiemposKmedianas(puntos16_3, 3, 0.01)
probarKmedianasPar(puntos16_3, 3, 0.01)
probarKmedianasSeq(puntos16_3, 3, 0.01)


val puntos4096_64 = generarPuntos(64, 4096).toSeq
tiemposKmedianas(puntos4096_64, 64, 0.01)
probarKmedianasPar(puntos4096_64, 64, 0.01)
probarKmedianasSeq(puntos4096_64, 64, 0.01)

val puntos8192_128 = generarPuntos(128, 8192).toSeq
tiemposKmedianas(puntos8192_128, 128, 0.01)
probarKmedianasPar(puntos8192_128, 128, 0.01)
probarKmedianasSeq(puntos8192_128, 128, 0.01)

val puntos16384_256 = generarPuntos(256, 16384).toSeq
tiemposKmedianas(puntos16384_256, 256, 0.01)
probarKmedianasPar(puntos16384_256, 256, 0.01)
probarKmedianasSeq(puntos16384_256, 256, 0.01)

val puntos32768_512 = generarPuntos(512, 32768).toSeq
tiemposKmedianas(puntos32768_512, 512, 0.01)
probarKmedianasPar(puntos32768_512, 512, 0.01)
probarKmedianasSeq(puntos32768_512, 512, 0.01)

