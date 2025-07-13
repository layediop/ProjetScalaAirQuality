
// import kantan.csv._
// import kantan.csv.ops._
// import kantan.csv.generic._
// import java.nio.file.Paths

// case class AirQualityRecord(
//   Date: String,
//   Time: String,
//   CO_GT: Option[Double],
//   PT08_S1_CO: Option[Int],
//   NMHC_GT: Option[Int],
//   C6H6_GT: Option[Double],
//   PT08_S2_NMHC: Option[Int],
//   NOx_GT: Option[Int],
//   PT08_S3_NOx: Option[Int],
//   NO2_GT: Option[Int],
//   PT08_S4_NO2: Option[Int],
//   PT08_S5_O3: Option[Int],
//   T: Option[Double],
//   RH: Option[Double],
//   AH: Option[Double]
// )

// object DataProcessing {

//   // Header decoder en respectant le nom exact des colonnes et le séparateur ';'
//   implicit val decoder: HeaderDecoder[AirQualityRecord] = HeaderDecoder.decoder(
//     "Date", "Time", "CO(GT)", "PT08.S1(CO)", "NMHC(GT)", "C6H6(GT)", "PT08.S2(NMHC)",
//     "NOx(GT)", "PT08.S3(NOx)", "NO2(GT)", "PT08.S4(NO2)", "PT08.S5(O3)", "T", "RH", "AH"
//   ) {
//     (
//       Date: String, Time: String, co: String, pt08_1: String, nmhc: String, c6h6: String, pt08_2: String,
//       nox: String, pt08_3: String, no2: String, pt08_4: String, pt08_5: String, t: String, rh: String, ah: String
//     ) =>
//       AirQualityRecord(
//         Date,
//         Time,
//         parseDouble(co),
//         parseInt(pt08_1),
//         parseInt(nmhc),
//         parseDouble(c6h6),
//         parseInt(pt08_2),
//         parseInt(nox),
//         parseInt(pt08_3),
//         parseInt(no2),
//         parseInt(pt08_4),
//         parseInt(pt08_5),
//         parseDouble(t),
//         parseDouble(rh),
//         parseDouble(ah)
//       )
//   }

//   // Fonctions de parsing avec gestion des virgules
//   private def parseDouble(s: String): Option[Double] =
//     if (s.trim.isEmpty) None else Some(s.replace(',', '.').toDouble)

//   private def parseInt(s: String): Option[Int] =
//     if (s.trim.isEmpty) None else Some(s.toInt)

//   def readCSV(path: String): List[AirQualityRecord] = {
//     val p = Paths.get(path)
//     p.asCsvReader[AirQualityRecord](rfc.withHeader.withCellSeparator(';')).collect {
//       case Right(record) => record
//     }.toList
//   }

//   def meanCO_GT(records: List[AirQualityRecord]): Option[Double] = {
//     val values = records.flatMap(_.CO_GT)
//     if (values.isEmpty) None else Some(values.sum / values.size)
//   }

//   def maxCO_GT(records: List[AirQualityRecord]): Option[Double] =
//     records.flatMap(_.CO_GT).reduceOption(_ max _)

//   def minCO_GT(records: List[AirQualityRecord]): Option[Double] =
//     records.flatMap(_.CO_GT).reduceOption(_ min _)

//   def countAboveThreshold(records: List[AirQualityRecord], threshold: Double): Int =
//     records.count(r => r.CO_GT.exists(_ > threshold))

//   def filterAboveThreshold(records: List[AirQualityRecord], threshold: Double): List[AirQualityRecord] =
//     records.filter(r => r.CO_GT.exists(_ > threshold))

//   def histogramCO_GT(records: List[AirQualityRecord], bins: Int): Unit = {
//     val values = records.flatMap(_.CO_GT)
//     if (values.isEmpty) {
//       println("Pas de données CO_GT.")
//       return
//     }
//     val minVal = values.min
//     val maxVal = values.max
//     val binSize = (maxVal - minVal) / bins

//     val histogram = Array.fill(bins)(0)
//     values.foreach { v =>
//       val bin = ((v - minVal) / binSize).toInt.min(bins - 1)
//       histogram(bin) += 1
//     }

//     println("Histogramme CO_GT :")
//     for (i <- histogram.indices) {
//       val lower = minVal + i * binSize
//       val upper = lower + binSize
//       val bar = "*" * (histogram(i) / 10 + 1)
//       println(f"$lower%.2f - $upper%.2f : $bar")
//     }
//   }
// }

import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._
import java.nio.file.Paths

case class AirQualityRecord(
  Date: String,
  Time: String,
  CO_GT: Option[Double],
  PT08_S1_CO: Option[Int],
  NMHC_GT: Option[Int],
  C6H6_GT: Option[Double],
  PT08_S2_NMHC: Option[Int],
  NOx_GT: Option[Int],
  PT08_S3_NOx: Option[Int],
  NO2_GT: Option[Int],
  PT08_S4_NO2: Option[Int],
  PT08_S5_O3: Option[Int],
  T: Option[Double],
  RH: Option[Double],
  AH: Option[Double]
)

object DataProcessing {

  // On précise le séparateur ';' car c’est un CSV avec point-virgule
  implicit val decoder: HeaderDecoder[AirQualityRecord] = HeaderDecoder.decoder(
    "Date", "Time", "CO(GT)", "PT08.S1(CO)", "NMHC(GT)", "C6H6(GT)", "PT08.S2(NMHC)",
    "NOx(GT)", "PT08.S3(NOx)", "NO2(GT)", "PT08.S4(NO2)", "PT08.S5(O3)", "T", "RH", "AH"
  ) {
    (
      Date: String, Time: String, co: String, pt08_1: String, nmhc: String, c6h6: String, pt08_2: String,
      nox: String, pt08_3: String, no2: String, pt08_4: String, pt08_5: String, t: String, rh: String, ah: String
    ) =>
      AirQualityRecord(
        Date,
        Time,
        parseDouble(co),
        parseInt(pt08_1),
        parseInt(nmhc),
        parseDouble(c6h6),
        parseInt(pt08_2),
        parseInt(nox),
        parseInt(pt08_3),
        parseInt(no2),
        parseInt(pt08_4),
        parseInt(pt08_5),
        parseDouble(t),
        parseDouble(rh),
        parseDouble(ah)
      )
  }

  // Remplace virgule par point et parse en Double
  private def parseDouble(s: String): Option[Double] =
    if (s.trim.isEmpty) None else Some(s.replace(',', '.').toDouble)

  private def parseInt(s: String): Option[Int] =
    if (s.trim.isEmpty) None else Some(s.toInt)

  def readCSV(path: String): List[AirQualityRecord] = {
    val p = Paths.get(path)
    p.asCsvReader[AirQualityRecord](rfc.withHeader.withCellSeparator(';')).collect {
      case Right(record) => record
    }.toList
  }

  // Filtre uniquement les valeurs CO_GT valides (> 0)
  private def validCO_GT(records: List[AirQualityRecord]): List[Double] =
    records.flatMap(_.CO_GT).filter(_ > 0)

  def meanCO_GT(records: List[AirQualityRecord]): Option[Double] = {
    val values = validCO_GT(records)
    if (values.isEmpty) None else Some(values.sum / values.size)
  }

  def maxCO_GT(records: List[AirQualityRecord]): Option[Double] =
    validCO_GT(records).reduceOption(_ max _)

  def minCO_GT(records: List[AirQualityRecord]): Option[Double] =
    validCO_GT(records).reduceOption(_ min _)

  def countAboveThreshold(records: List[AirQualityRecord], threshold: Double): Int =
    records.count(r => r.CO_GT.exists(v => v > threshold && v > 0))

  def filterAboveThreshold(records: List[AirQualityRecord], threshold: Double): List[AirQualityRecord] =
    records.filter(r => r.CO_GT.exists(v => v > threshold && v > 0))

  def histogramCO_GT(records: List[AirQualityRecord], bins: Int): Unit = {
    val values = validCO_GT(records)
    if (values.isEmpty) {
      println("Pas de données CO_GT valides.")
      return
    }
    val minVal = values.min
    val maxVal = values.max
    val binSize = (maxVal - minVal) / bins

    val histogram = Array.fill(bins)(0)
    values.foreach { v =>
      val bin = ((v - minVal) / binSize).toInt.min(bins - 1)
      histogram(bin) += 1
    }

    println("Histogramme CO_GT :")
    for (i <- histogram.indices) {
      val lower = minVal + i * binSize
      val upper = lower + binSize
      val bar = "*" * (histogram(i) / 10 + 1)
      println(f"$lower%.2f - $upper%.2f : $bar")
    }
  }
}
