ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "airqualityfunctional",
    version := "0.1",
    libraryDependencies ++= Seq(
      "com.nrinaudo" %% "kantan.csv" % "0.6.1",
      "com.nrinaudo" %% "kantan.csv-generic" % "0.6.1",
      "org.knowm.xchart" % "xchart" % "3.8.4"
    )
  )
