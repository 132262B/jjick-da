package com.jjickda.domain.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;


@ApiIgnore
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/adminpage")
    public String AdminPage() {
        return "main/admin/adminPage";
    }

    // write-question
    @GetMapping("/write-question")
    public String questionWrite() {
        return "main/admin/writeQuestion";
    }

    @GetMapping("/write-question-form")
    public String questionWriteForm() {
        return "main/admin/writeQuestionForm";
    }

    @GetMapping("/write-main-question")
    public String mainQuestionWriteForm() {
        return "main/admin/writeMainQuestionForm";
    }

    @GetMapping("/write-sub-question")
    public String subQuestionWriteForm() {
        return "main/admin/writeSubQuestionForm";
    }

    @GetMapping("/question-list")
    public String questionList() {
        return "main/admin/questionList";
    }
    @GetMapping("/test222")
    public String test() {
        return "main/admin/test";
    }

}
