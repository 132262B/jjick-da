package app.jjickda.domain.user.controller;

import app.jjickda.domain.common.dto.response.DefaultResultDto;
import app.jjickda.domain.user.dto.request.EmailDuplicationDto;
import app.jjickda.domain.user.dto.request.LoginDto;
import app.jjickda.domain.user.dto.request.SignUpDto;
import app.jjickda.domain.user.dto.response.User;
import app.jjickda.domain.user.service.UserService;
import app.jjickda.global.config.model.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "사용자 관련 API")
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @ApiOperation(value = "이메일 중복 조회.", notes = "이메일 중복 체크를 위한 API")
    @PostMapping("/email-duplication")
    public ResponseEntity<ApiResponse<DefaultResultDto>> emailDuplicationCheck(@Validated @RequestBody EmailDuplicationDto emailDuplicationDto) {
        return ResponseEntity.ok(new ApiResponse<>(userService.emailDuplicationCheck(emailDuplicationDto)));
    }

    @ApiOperation(value = "회원 정보 등록.", notes = "회원 정보 등록을 위한 API")
    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<DefaultResultDto>> signUp(@Validated @RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(new ApiResponse<>(userService.signUp(signUpDto)));
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<DefaultResultDto>> login(@Validated @RequestBody LoginDto loginDto) {
        User user = userService.getUser(loginDto);
        return ResponseEntity.ok(new ApiResponse<>(userService.login(user)));
    }

    @ApiOperation(value = "로그아웃")
    @GetMapping("/logout")
    public ResponseEntity<ApiResponse<DefaultResultDto>> login() {
        return ResponseEntity.ok(new ApiResponse<>(userService.logout()));
    }

}
