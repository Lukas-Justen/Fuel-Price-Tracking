package de.developer.lukasjusten.fuel.price.scraper.model

import java.time.OffsetDateTime

data class GasStationSummary(
  val name: String,
  val url: String,
  val timestamp: OffsetDateTime,
  val gasoline: Double?,
  val gasolineE10: Double?,
  val diesel: Double?
)
