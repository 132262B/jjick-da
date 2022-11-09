package app.jjickda.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "main/login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "main/signUp";
    }
}
