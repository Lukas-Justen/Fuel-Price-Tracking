package de.developer.lukasjusten.fuel.price.scraper

import de.developer.lukasjusten.fuel.price.scraper.model.*
import org.jboss.logging.Logger
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.time.OffsetDateTime
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PriceScrapeService {

  companion object {
    private const val USER_AGENT = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"
    private const val REFERRER = "http://www.google.com"
    private const val FUEL_PRICE_ELEMENT = "div.preis"
  }

  private val log = Logger.getLogger(PriceScrapeService::class.java)

  fun requestFuelPrices(gasStation: GasStation): List<CurrentFuelPrice> {
    val currentTimestamp = OffsetDateTime.now()
    return fetchPage(gasStation.url)
      ?.select(FUEL_PRICE_ELEMENT)
      ?.mapNotNull { CurrentFuelPriceMapper.toFuelPrice(currentTimestamp, it) }
      ?: emptyList()
  }

  private fun fetchPage(url: String): Document? {
    try {
      log.info("Fetch HTML document at $url")
      return Jsoup.connect(url).userAgent(USER_AGENT).referrer(REFERRER).get()
    } catch (exception: HttpStatusException) {
      log.debug("Could not scrape $url due to ${exception.message}")
      return null
    }
  }
}