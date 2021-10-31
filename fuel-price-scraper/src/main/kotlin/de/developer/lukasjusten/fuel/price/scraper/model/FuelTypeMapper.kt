package de.developer.lukasjusten.fuel.price.scraper.model

object FuelTypeMapper {

  fun toFuelType(fuelTypeText: String): FuelType? {
    return FuelType.values()
      .associateBy { it.text }
      .get(fuelTypeText)
  }
}