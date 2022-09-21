package com.jjickda.global.config.error.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorPageController implements ErrorController {

    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (String.valueOf(status).equalsIgnoreCase(HttpStatus.NOT_FOUND.toString())) {
            return "errors/404";
        }
        return "errors/404";
    }

    // 2.4.5 버전에서는 아래 메서드 Override 받았음.
//    @Override
//    public String getErrorPath() {
//        return "error";
//    }
}
