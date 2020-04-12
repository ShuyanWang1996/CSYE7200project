name := "source"

version := "0.1"

scalaVersion := "2.12.0"

//val casbah = "org.mongodb" %% "casbah" % "2.5.0"
val sprayGroup = "io.spray"
val sprayJsonVersion = "1.3.5"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.9.0"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.5"
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.4.5"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.5"
libraryDependencies += "org.mongodb.spark" %% "mongo-spark-connector" % "2.4.1"
libraryDependencies += "org.mongodb" % "bson" % "4.0.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test"
libraryDependencies += "org.mongodb" %% "casbah" % "3.1.1" pomOnly()
libraryDependencies ++= List("spray-json") map {c => sprayGroup %% c % sprayJsonVersion}
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.0"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.3"

