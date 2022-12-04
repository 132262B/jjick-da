package app.jjickda.api.choice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ChoiceController {

    // 자격증 선택 페이지
    @GetMapping("/choice")
    public String SelectQuestion() { return "main/user/examChoice"; }

}
