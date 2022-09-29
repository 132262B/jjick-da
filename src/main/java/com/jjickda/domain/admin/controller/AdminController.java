package com.jjickda.domain.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;


// 어드민 컨트롤러들은 앞에 /admin이 붙어야 나중에 aop 등을 사용해서 권한 로직 처리에 편함이 생길꺼 같음.
// 확인하고 아래 로직 주석풀고 작업 진행.
@ApiIgnore
@Controller
//@RequestMapping("/admin")
public class AdminController {

    // 주소에 구분자마다 하이픈(-) 를 추가하면 좋을꺼같음.
    @GetMapping("/adminpage")
    public String AdminPage() {
        return "main/admin/adminPage";
    }

    // write-question
    @GetMapping("/writequestion")
    public String questionWrite() {
        return "main/admin/writeQuestion";
    }

    @GetMapping("/writequestionform")
    public String questionWriteForm() {
        return "main/admin/writeQuestionForm";
    }

    @GetMapping("/writemainquestion")
    public String mainQuestionWriteForm() {
        return "main/admin/writeMainQuestionForm";
    }

    @GetMapping("/writesubquestion")
    public String subQuestionWriteForm() {
        return "main/admin/writeSubQuestionForm";
    }

    @GetMapping("/questionlist")
    public String questionList() {
        return "main/admin/questionList";
    }

}
