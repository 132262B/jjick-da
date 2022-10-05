package com.jjickda.domain.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ExamController {

    @GetMapping("/exam-single")
    public String examSingle(Model model) {
        return "main/user/exam-single";
    }

    @GetMapping("/exam-all")
    public String examAll(Model model) {
        return "main/user/exam-all";
    }

    @GetMapping("/exam-result")
    public String examResult(Model model) {
        return "main/user/exam-result";
    }

}
