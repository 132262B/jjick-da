package com.jjickda.domain.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/adminPage")
    public String AdminPage(Model model) {
        return "main/admin/adminPage";
    }
    @GetMapping("/writeQuestion")
    public String questionWrite(Model model){
        return "main/admin/writeQuestion";
    }

    @GetMapping("/writeQuestionForm")
    public String questionWriteForm(Model model){
        return "main/admin/writeQuestionForm";
    }
}
