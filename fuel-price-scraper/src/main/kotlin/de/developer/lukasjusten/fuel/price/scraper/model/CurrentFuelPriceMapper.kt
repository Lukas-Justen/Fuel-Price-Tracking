package de.developer.lukasjusten.fuel.price.scraper.model

import org.jsoup.nodes.Element
import java.time.OffsetDateTime

object CurrentFuelPriceMapper {

  private const val FUEL_ELEMENT = "strong"
  private const val PRICE_ELEMENT = "span.zahl"

  fun toFuelPrice(currentTimestamp: OffsetDateTime, htmlElement: Element): CurrentFuelPrice? {
    val fuelType = getFuelType(htmlElement)
    val fuelPrice = getFuelPrice(htmlElement)
    if (fuelType != null && fuelPrice != null) {
      return CurrentFuelPrice(
        fuelType,
        fuelPrice,
        currentTimestamp
      )
    }
    return null
  }

  private fun getFuelType(htmlElement: Element): FuelType? {
    return htmlElement.select(FUEL_ELEMENT)
      .firstOrNull()
      ?.text()
      ?.let { FuelTypeMapper.toFuelType(it) }
  }

  private fun getFuelPrice(htmlElement: Element): Double? {
    return htmlElement.select(PRICE_ELEMENT)
      .firstOrNull()
      ?.text()
      ?.replace("€", "")
      ?.replace(",", ".")
      ?.toDoubleOrNull()
  }
}