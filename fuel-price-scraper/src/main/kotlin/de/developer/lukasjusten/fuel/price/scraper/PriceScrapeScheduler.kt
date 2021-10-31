package de.developer.lukasjusten.fuel.price.scraper

import io.quarkus.scheduler.Scheduled
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class PriceScrapeScheduler {

  private val log = Logger.getLogger(PriceScrapeScheduler::class.java)

  @Inject
  lateinit var priceScrapeService: PriceScrapeService;

//  @Scheduled(cron = "*/15 * * * *")
//  fun scrape() {
//    log.info("Start scraping process")
//  }
}