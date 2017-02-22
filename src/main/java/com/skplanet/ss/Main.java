//package com.skplanet.ss;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * Created on 2017. 2. 17..
// */
//@Slf4j
//public class Main {
//
//    public static void main(String[] args) throws Exception {
//
////        String url = "https://accounts.google.com/ServiceLoginAuth";
////        String gmail = "https://mail.google.com/mail/";
//        String url = "https://accounts.google.com/ServiceLoginAuth";
//        String gmail = "https://mail.google.com/mail/";
//
//        log.info("========================================================");
//        naverTodayStock();
//        log.info("========================================================");
//        ppomppuLogin();
//
//        log.info("========================================================");
//
//
//
//        // 1. Send a "GET" request, so that you can extract the form's data.
////        String page = http.GetPageContent(url);
////        log.info(page);
////
////        String postParams = http.getFormParams(page, "username@gmail.com", "password");
////
////        // 2. Construct above post's content and then send a POST request for
////        // authentication
////        http.sendPost(url, postParams);
////
////        // 3. success then go to gmail.
////        String result = http.GetPageContent(gmail);
////        System.out.println(result);
//    }
//
//    private static void naverTodayStock() throws Exception {
//
//        HttpConnection http = new HttpConnection();
//
//        // make sure cookies is turn on
////        CookieHandler.setDefault(new CookieManager());
//
//        String todayStock = http.getPageContent("http://finance.naver.com", "EUC-KR");
//        NaverTodayStock.getTodayStock(todayStock);
//    }
//
//    private static void ppomppuLogin() throws Exception  {
//
//        HttpConnection http = new HttpConnection();
//
////        http.sendPost("https://www.ppomppu.co.kr/zboard/login_check.php", PpomppuLogin.getFormParams());
//
//        String ppomppuMemo = http.getPageContent("https://www.ppomppu.co.kr/zboard/member_memo.php", "EUC-KR");
//        log.info(ppomppuMemo);
//    }
//
//}
