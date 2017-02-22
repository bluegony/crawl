package com.skplanet.ss.service;

import com.skplanet.ss.domain.TodayStock;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created on 2017. 2. 15..
 */
@Slf4j
public class NaverTodayStock {


    public static String getTodayStock(String page) {
        Document doc = Jsoup.parse(page);

        String a = new String();
        a += parseTodayStockData(doc, "kospi_area");
        a += "<br>"+parseTodayStockData(doc, "kosdaq_area");
        a += "<br>"+parseTodayStockData(doc, "kospi200_area");

        Element el = doc.getElementsByClass("aside_area aside_stock").first();
        a += el.toString();
        return a;
    }

    private static String parseTodayStockData(Document doc, String className) {

        Element el = doc.getElementsByClass(className).first(); // kosdaq_area //kospi_area

        TodayStock todayStock = new TodayStock();
        todayStock.setName(el.select("h4 em").first().text());
        todayStock.setPriceIndex(el.getElementsByClass("num").first().text());
        todayStock.setFluctuationPercent(el.getElementsByClass("num3").first().text());
        todayStock.checkMinusAndSetFluctuation(el.getElementsByClass("num2").first().text());
        todayStock.setIndividual( el.select("dt:contains(개인) + dd").first().text());
        todayStock.setForeigner( el.select("dt:contains(외국인) + dd").first().text());
        todayStock.setInstitution( el.select("dt:contains(기관) + dd").first().text());


        log.info(todayStock.toString());
        return todayStock.toString();
    }

//    private static String parseWorldStockData(Document doc, String className) {
//
//    }


}
