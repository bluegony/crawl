package com.skplanet.ss.service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created on 2017. 2. 20..
 */

@Slf4j
public class NaverLogin {

    private static final String URL_LOGIN
            = "http://static.nid.naver.com/login.nhn?svc=wme&amp;amp;url=http%3A%2F%2Fwww.naver.com&amp;amp;t=20120425";

    private static final String URL_WT_CAFE             = "http://cafe.naver.com/bodygood";

    private static final String URL_MAIL                = "https://mail.naver.com/";

    private static final String URL_MY_STOCK            = "http://finance.naver.com/mystock/itemList.nhn";

    private WebClient webClient;
    private HtmlPage currPage;

    public NaverLogin(String naverId, String naverPw) throws Exception {
        webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);
        webClient.waitForBackgroundJavaScript(5000);
        if(login(naverId, naverPw)) {
            log.info("login error!!!!!");
//            throw new Exception("Login Fail!!!");
        }

    }

    private boolean login(String naverId, String naverPw) throws Exception {
        currPage = webClient.getPage(URL_LOGIN);

        HtmlForm form = currPage.getFormByName("frmNIDLogin");
        HtmlTextInput inputId = form.getInputByName("id");
        HtmlPasswordInput inputPw = form.getInputByName("pw");
        HtmlImageInput button = (HtmlImageInput)form .getByXPath("//input[@alt='로그인']").get(0);

        inputId.setValueAttribute(naverId);
        inputPw.setValueAttribute(naverPw);
        currPage = (HtmlPage)button.click();


        if(currPage.asText().contains("new device(browser)")) {
            log.info("Try to register New Browser");
            ScriptResult result = currPage.executeJavaScript("$('regyn').value ='Y';$('frmNIDLogin').submit();");
            result.getJavaScriptResult();
            currPage = (HtmlPage)result.getNewPage();
        } else if(currPage.asText().contains("Naver Sign in"))  {
            log.info(currPage.asXml());
            log.info("Login Fail!!");
        } else {
            log.info("Login success!!!!!");
        }




        return currPage.asText().contains("Naver Sign in");
    }

    public String getMyStock() throws IOException {
        currPage = webClient.getPage(URL_MY_STOCK);
        HtmlTable table = currPage.getFirstByXPath("//table[@summary='MY STOCK 리스트']");
        log.info(table.asText());
        String stock = table.asText();
        stock = stock.replace("unchecked","<br>");
        stock = stock.replace("바로 입력 상세 매매 내역 보기","");
        stock = stock.replace("매매내역","");
        return stock;

    }


}


