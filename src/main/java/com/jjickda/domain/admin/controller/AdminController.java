package com.jjickda.domain.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class AdminController {

    @GetMapping("/adminpage")
    public String AdminPage(Model model) {
        return "main/admin/adminPage";
    }
    @GetMapping("/writequestion")
    public String questionWrite(Model model){
        return "main/admin/writeQuestion";
    }

    @GetMapping("/writequestionform")
    public String questionWriteForm(Model model){
        return "main/admin/writeQuestionForm";
    }
}
