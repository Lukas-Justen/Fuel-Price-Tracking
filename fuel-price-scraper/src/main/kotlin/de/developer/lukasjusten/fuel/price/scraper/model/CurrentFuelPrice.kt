package de.developer.lukasjusten.fuel.price.scraper.model

import java.time.OffsetDateTime

data class CurrentFuelPrice(
  val fuelType: FuelType,
  val price: Double,
  val timestamp: OffsetDateTime
)