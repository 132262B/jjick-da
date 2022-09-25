package com.jjickda.domain.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class MainController {

    // 메인 페이지
    @GetMapping("/home")
    public String home(Model model) {
        return "main/home";
    }

}
