package de.developer.lukasjusten.fuel.price.scraper.model

object FuelTypeConverter {

  fun to(fuelTypeText: String): FuelType? {
    return when (fuelTypeText) {
      "Super Benzin" -> FuelType.GASOLINE
      "Super (E10) Benzin" -> FuelType.GASOLINE_E10
      "Diesel" -> FuelType.DIESEL
      else -> null
    }
  }
}