package app.jjickda.api.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ExamController {

    @GetMapping("/exam-single")
    public String examSingle(Model model) {
        return "main/user/examSingle";
    }

    @GetMapping("/exam-all")
    public String examAll(Model model) {
        return "main/user/examAll";
    }

    @GetMapping("/exam-result")
    public String examResult(Model model) {
        return "main/user/examResult";
    }

}
