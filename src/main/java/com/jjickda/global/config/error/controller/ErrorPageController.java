package com.jjickda.global.config.error.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorPageController implements ErrorController {

    // 공통 에러페이지
    @GetMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        model.addAttribute("status", status.toString());
        return "errors/error";
    }

    // 2.4.5 버전에서는 아래 메서드 Override 받았음.
//    @Override
//    public String getErrorPath() {
//        return "error";
//    }
}
