package com.skplanet.ss.service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017. 2. 17..
 */
@Slf4j
public class PpomppuLogin {

    private static final String URL_LOGIN
            = "http://ppomppu.co.kr/";
    private static final String URL_MEMO
            = "https://www.ppomppu.co.kr/zboard/member_memo.php";
    private static final String URL_WT_CAFE
            = "http://cafe.naver.com/bodygood";

    private WebClient webClient;
    private HtmlPage currPage;

    public PpomppuLogin(String naverId, String naverPw) throws Exception {
        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.waitForBackgroundJavaScript(5000);
        if(!login(naverId, naverPw)) {
            log.info("login fail");
//            throw new Exception("cannot login with the id and pw");
        }
        else {
            log.info("login success");
        }
    }

    private boolean login(String naverId, String naverPw) throws Exception {
        currPage = webClient.getPage(URL_LOGIN);

        HtmlForm form = currPage.getFormByName("zb_login");
        HtmlTextInput inputId = form.getInputByName("user_id");
        HtmlPasswordInput inputPw = (HtmlPasswordInput)form.getInputByName("password");
        HtmlImageInput button = (HtmlImageInput)form
                .getByXPath("//input[@src='/images/main/201309/btn_login.jpg']").get(0);

        inputId.setValueAttribute(naverId);
        inputPw.setValueAttribute(naverPw);
        currPage = (HtmlPage)button.click();

        return currPage.asText().contains("푸른고니");
    }


    public void getMemo() throws IOException {
        currPage = webClient.getPage(URL_MEMO);
        log.info(currPage.asText());

    }
//    public static void main(String[] args) throws Exception {
//
//        String naverId = "bluegony";
//        String naverPw = "testtest";
//        PpomppuLogin naver = new PpomppuLogin(naverId, naverPw);
//
//        naver.getMemo();
//
//    }

}
