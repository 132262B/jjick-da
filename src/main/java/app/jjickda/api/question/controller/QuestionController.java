package app.jjickda.api.question.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class QuestionController {

    @GetMapping("/select-certificate")
    public String SelectQuestion() {
        return "main/user/exam-select-certificate";
    }

    @GetMapping("/select-term/{questionSeq}")
    public String SelectTerm(@PathVariable long questionSeq) {
        return "main/user/exam-select-term";
    }

}
