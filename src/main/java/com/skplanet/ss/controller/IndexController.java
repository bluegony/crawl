package com.skplanet.ss.controller;

import com.skplanet.ss.service.HttpConnection;
import com.skplanet.ss.service.NaverLogin;
import com.skplanet.ss.service.NaverTodayStock;
import org.springframework.boot.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@EnableAutoConfiguration
@RequestMapping("/")
public class IndexController {

//    @RequestMapping("input")
//    @ResponseBody
//    String home() {
//        return "Hello World!";
//    }

    @RequestMapping("input")
    @ResponseBody
    ModelAndView input() {
        return new ModelAndView("input");
    }

    @RequestMapping("index")
    @ResponseBody
    ModelAndView index() {

        return new ModelAndView("index");
    }

    @RequestMapping("naverLogin")
    @ResponseBody
    public String naverLogin(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String password) throws Exception {
        NaverLogin naver = new NaverLogin(id, password);
        return naver.getMyStock();

//        return "naverLogin";
    }

    @RequestMapping("naverToday")
    @ResponseBody
    public String naverToday() throws Exception {
        HttpConnection http = new HttpConnection();
        String todayStock = http.getPageContent("http://finance.naver.com", "EUC-KR");
        return NaverTodayStock.getTodayStock(todayStock);
    }

    @RequestMapping("/")
    public String index(Model model) {
        return "redirect:"+"naverToday";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IndexController.class, args);
    }
}