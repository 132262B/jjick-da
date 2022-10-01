package com.jjickda.domain.question.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class QuestionController {

    @GetMapping("/select-question")
    public String SelectQuestion() {
        return "main/user/exam-select-question";
    }

    @GetMapping("/select-term")
    public String SelectTerm() {
        return "main/user/exam-select-term";
    }

}
