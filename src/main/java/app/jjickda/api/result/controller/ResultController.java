package app.jjickda.api.result.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ResultController {

    @GetMapping("/result")
    public String examResult(@RequestParam long idx, @RequestParam String token, Model model) {
        model.addAttribute("idx", idx);
        model.addAttribute("token", token);
        return "main/user/examResult";
    }

}
