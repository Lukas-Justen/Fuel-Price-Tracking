package de.developer.lukasjusten.fuel.price.scraper.dto

import java.time.OffsetDateTime

data class FuelPriceSummaryDTO(
  val gasStation: String,
  val timestamp: OffsetDateTime,
  val gasoline: Double?,
  val diesel: Double?
)
