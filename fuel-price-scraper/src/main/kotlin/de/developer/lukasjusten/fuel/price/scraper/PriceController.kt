package de.developer.lukasjusten.fuel.price.scraper

import de.developer.lukasjusten.fuel.price.scraper.dto.FuelPriceSummaryDTO
import de.developer.lukasjusten.fuel.price.scraper.dto.FuelPriceSummaryMapper
import de.developer.lukasjusten.fuel.price.scraper.model.GasStation
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/prices/stromberg")
class PriceController {

  companion object {
    private val TEST_GAS_STATION = GasStation(
      "Stromberg",
      "https://ich-tanke.de/tankstelle/6f00c71d7a6ff963aa99f73ae481a687/"
    )
  }

  @Inject
  lateinit var priceScrapeService: PriceScrapeService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun requestPrice(): FuelPriceSummaryDTO {
    return priceScrapeService.requestFuelPrices(TEST_GAS_STATION)
      .let { FuelPriceSummaryMapper.toDto(TEST_GAS_STATION, it) }
  }
}