import org.knowm.xchart.{CategoryChartBuilder, SwingWrapper}
import scala.jdk.CollectionConverters._

object Visualization {
  def plotHistogram(values: Seq[Double], bins: Int): Unit = {
    if (values.isEmpty) {
      println("Pas de données pour la visualisation.")
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

    // indices pour xData
    val xData: java.util.List[Integer] = (0 until bins).map(Int.box).toList.asJava
    val yData: java.util.List[java.lang.Double] = histogram.map(_.toDouble).map(Double.box).toList.asJava

    val chart = new CategoryChartBuilder()
      .width(800).height(600)
      .title("Histogramme CO_GT")
      .xAxisTitle("Bins")
      .yAxisTitle("Fréquence")
      .build()

    chart.addSeries("CO_GT", xData, yData)

    new SwingWrapper(chart).displayChart()
  }
}
