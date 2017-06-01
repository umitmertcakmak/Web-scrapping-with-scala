package com.scrappers.basic

/**
  * Created by umitcakmak on 31/05/17.
  */

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import java.io._

import net.ruippeixotog.scalascraper.scraper.ContentExtractors.element

import scala.util.Try

object BasicScrapper extends App{

  val browser = JsoupBrowser()
  val doc = browser.get("https://www.bloomberg.com/europe")

  val a_s = doc >> elementList("a")

  val writer = new PrintWriter(new File("elements.txt" ))

  a_s.map(e => Try(writer.write(e.attr("href").toString + "\n")))

  writer.close()

  // pulling

//  val a_elements = doc >> elementList(".markets-bar-collection a")
//
//  val links = a_elements.map(_ >> attr("href"))
//
//  println(links)
//
//  val content = doc >> text(".home__markets-bar")
//
//  val latest = doc >> elementList(".markets-bar-item__link")
//  val topnews = doc >> elementList(".top-news-v3-story-thumbnail")
//
//  println("Latest News")
//  latest.map(e => println(e.attr("href")))
//  println("Top News")
//  topnews.map(e => println(e.attr("href")))
//  val latestnews = browser.get(latest(0).attr("href"))
//
//  val newsbody = latestnews >> text(".body-copy")
//
//  println(newsbody)



}
