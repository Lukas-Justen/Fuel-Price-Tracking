package de.developer.lukasjusten.fuel.price.scraper

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/prices")
class PriceResource {

  @Inject
  lateinit var priceScrapeService: PriceScrapeService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun hello() = priceScrapeService.scrape()!!
}