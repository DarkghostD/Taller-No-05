import sbt._
import Keys._

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "Taller 05",
    libraryDependencies ++= Seq(
      "org.plotly-scala" %% "plotly-render" % "0.8.1",
      "org.plotly-scala" %% "plotly-core" % "0.8.1",
      "com.storm-enroute" %% "scalameter" % "0.19",
      "com.storm-enroute" %% "scalameter-core" % "0.21" cross CrossVersion.for3Use2_13,
      "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.3",
      "org.scalameta" %% "munit" % "0.7.26" % Test
    ),
    scalacOptions ++= Seq("-language:implicitConversions", "-deprecation")
  )
