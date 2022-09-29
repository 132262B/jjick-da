package com.jjickda.domain.question.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class QuestionController {

    @GetMapping("/select")
    public String Select(Model model) {
        return "main/user/exam-select";
    }

    @GetMapping("/selectNext")
    public String SelectNext() {
        return "main/user/exam-select-next";
    }


}
