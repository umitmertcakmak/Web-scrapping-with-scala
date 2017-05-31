package com.scrappers.basic

/**
  * Created by umitcakmak on 31/05/17.
  */

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._

object BasicScrapper extends App{

  val browser = JsoupBrowser()
  val doc = browser.get("https://www.bloomberg.com/europe")


  val content = doc >> text(".home__markets-bar")

  val latest = doc >> elementList(".markets-bar-item__link")
  val topnews = doc >> elementList(".top-news-v3-story-thumbnail")

  println("Latest News")
  latest.map(e => println(e.attr("href")))
  println("Top News")
  topnews.map(e => println(e.attr("href")))
//  val latestnews = browser.get(latest(0).attr("href"))
//
//  val newsbody = latestnews >> text(".body-copy")
//
//  println(newsbody)



}
