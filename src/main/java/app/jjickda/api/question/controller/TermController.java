package app.jjickda.api.question.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class TermController {

    @GetMapping("/choice")
    public String SelectQuestion() { return "main/user/examChoice"; }

}
