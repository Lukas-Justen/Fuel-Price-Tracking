package de.developer.lukasjusten.fuel.price.scraper.dto

import de.developer.lukasjusten.fuel.price.scraper.model.CurrentFuelPrice
import de.developer.lukasjusten.fuel.price.scraper.model.FuelType
import de.developer.lukasjusten.fuel.price.scraper.model.GasStation
import java.time.OffsetDateTime

object FuelPriceSummaryMapper {

  fun toDto(gasStation: GasStation, currentFuelPrices: List<CurrentFuelPrice>): FuelPriceSummaryDTO {
    return FuelPriceSummaryDTO(
      gasStation.name,
      getSummaryTimestamp(currentFuelPrices),
      getFuelTypePrice(currentFuelPrices, FuelType.GASOLINE),
      getFuelTypePrice(currentFuelPrices, FuelType.DIESEL),
    )
  }

  private fun getSummaryTimestamp(currentFuelPrices: List<CurrentFuelPrice>) : OffsetDateTime {
    return currentFuelPrices.map { it.timestamp }.maxOrNull() ?: OffsetDateTime.now()
  }

  private fun getFuelTypePrice(currentFuelPrices: List<CurrentFuelPrice>, fuelType: FuelType) : Double? {
    return currentFuelPrices.firstOrNull { it.fuelType == fuelType }?.price
  }
}