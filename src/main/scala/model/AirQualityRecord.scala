package model

import java.time.LocalDateTime

case class AirQualityRecord(
  dateTime: LocalDateTime,
  co: Option[Double],
  no2: Option[Double],
  c6h6: Option[Double],
  toluene: Option[Double]
)
