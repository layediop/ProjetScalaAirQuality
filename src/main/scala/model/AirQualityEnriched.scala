package model

import java.time.LocalDateTime

case class AirQualityEnriched(
  dateTime: LocalDateTime,
  co: Double,
  no2: Double,
  c6h6: Double,
  toluene: Double,
  indicePollution: Double,
  alerte: Boolean
)
