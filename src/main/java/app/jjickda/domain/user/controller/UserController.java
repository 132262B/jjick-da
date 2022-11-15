package app.jjickda.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    // 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "main/login";
    }

    // 회원가입 페이지
    @GetMapping("/sign-up")
    public String signUp() {
        return "main/signUp";
    }

    // 회원가입 후 축하 페이지
    @GetMapping("/welcome")
    public String welcome() {
        return "main/welcome";
    }
}
