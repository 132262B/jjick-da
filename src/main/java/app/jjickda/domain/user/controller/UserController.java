package app.jjickda.domain.user.controller;

import app.jjickda.domain.user.dto.response.User;
import app.jjickda.global.config.exception.ErrorCode;
import app.jjickda.global.config.exception.PagePermissionException;
import app.jjickda.global.utils.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    // 로그인 페이지
    @GetMapping("/login")
    public String login() {
        User user = SessionUtil.getUserAttribute();
        if (user != null)
            throw new PagePermissionException("로그인 상태로 로그인 페이지 방문", ErrorCode.HANDLE_ACCESS_DENIED);

        return "main/login";
    }

    // 회원가입 페이지
    @GetMapping("/sign-up")
    public String signUp() {
        User user = SessionUtil.getUserAttribute();
        if (user != null)
            throw new PagePermissionException("로그인 상태로 로그인 페이지 방문", ErrorCode.HANDLE_ACCESS_DENIED);

        return "main/signUp";
    }

    // 회원가입 후 축하 페이지
    @GetMapping("/welcome")
    public String welcome() {
        return "main/welcome";
    }
}
