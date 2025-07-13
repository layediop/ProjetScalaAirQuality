// object Main extends App {
//   val path = "AirQualityUCI.csv"
//   val records = DataProcessing.readCSV(path)

//   println(s"Nombre d'enregistrements : ${records.length}")
//   println(s"Moyenne CO_GT filtrée : ${DataProcessing.meanCO_GT(records)}")
//   println(s"Max CO_GT : ${DataProcessing.maxCO_GT(records)}")
//   println(s"Min CO_GT : ${DataProcessing.minCO_GT(records)}")
//   println(s"Nombre d'enregistrements avec CO_GT > 2.5 : ${DataProcessing.countAboveThreshold(records, 2.5)}")

//   println("\n5 premiers enregistrements filtrés (CO_GT > 2.5):")
//   val filtered = DataProcessing.filterAboveThreshold(records, 2.5)
//   if (filtered.isEmpty) println("Pas de données CO_GT > 2.5.") else filtered.take(5).foreach(println)

//   // Extraire les valeurs CO_GT filtrées (non vides)
//   val coValues = records.flatMap(_.CO_GT).filter(_ >= 0) // filtrer valeurs négatives si tu veux

//   // Appel de ta fonction de visualisation
//   Visualization.plotHistogram(coValues, 10)
// }

import java.io.PrintWriter
import java.io.{File, PrintWriter}

object Main extends App {
  val path = "AirQualityUCI.csv"
  val records = DataProcessing.readCSV(path)

  println(s"Nombre d'enregistrements : ${records.length}")
  println(s"Moyenne CO_GT filtrée : ${DataProcessing.meanCO_GT(records)}")
  println(s"Max CO_GT : ${DataProcessing.maxCO_GT(records)}")
  println(s"Min CO_GT : ${DataProcessing.minCO_GT(records)}")
  println(s"Nombre d'enregistrements avec CO_GT > 2.5 : ${DataProcessing.countAboveThreshold(records, 2.5)}")

  println("\n5 premiers enregistrements filtrés (CO_GT > 2.5):")
  val filtered = DataProcessing.filterAboveThreshold(records, 2.5)
  if (filtered.isEmpty) println("Pas de données CO_GT > 2.5.") else filtered.take(5).foreach(println)

  // Extraire les valeurs CO_GT filtrées (non vides)
  val coValues = records.flatMap(_.CO_GT).filter(_ >= 0)

  // Exporter dans un fichier CSV
  val writer = new PrintWriter(new File("co_gt_values.csv"))
  try {
    writer.println("CO_GT")
    coValues.foreach(value => writer.println(value))
  } finally {
    writer.close()
  }

  println("Fichier co_gt_values.csv généré pour visualisation.")
}
