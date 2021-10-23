package de.developer.lukasjusten.fuel.price.scraper

import de.developer.lukasjusten.fuel.price.scraper.model.FuelType
import de.developer.lukasjusten.fuel.price.scraper.model.FuelTypeConverter
import de.developer.lukasjusten.fuel.price.scraper.model.GasStation
import de.developer.lukasjusten.fuel.price.scraper.model.GasStationSummary
import org.jboss.logging.Logger
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.time.OffsetDateTime
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PriceScrapeService {

  companion object {
    private const val USER_AGENT = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"
    private const val REFERRER = "http://www.google.com"
    private const val GAS_STATION_URL = "https://ich-tanke.de/tankstelle/6f00c71d7a6ff963aa99f73ae481a687/"
  }

  private val log = Logger.getLogger(PriceScrapeService::class.java)

  fun scrape(gasStation: GasStation): GasStationSummary? {
    return fetchPage(gasStation.url)
      ?.select("div.preis")
      ?.associate { getFuelType(it) to getFuelPrice(it) }
      ?.filterKeys { it != null }
      ?.filterValues { it != null }
      ?.let {
        GasStationSummary(
          "Stromberg",
          GAS_STATION_URL,
          OffsetDateTime.now(),
          it as Map<FuelType, Double>
        )
      }
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

  private fun getFuelType(htmlElement: Element) : FuelType? {
    return htmlElement.select("strong")
      .firstOrNull()
      ?.text()
      ?.let { FuelTypeConverter.to(it) }
  }

  private fun getFuelPrice(htmlElement: Element) : Double? {
    return htmlElement.select("span.zahl")
      .firstOrNull()
      ?.text()
      ?.replace("€", "")
      ?.replace(",", ".")
      ?.toDoubleOrNull()
  }
}