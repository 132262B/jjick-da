package com.jjickda.domain.admin.controller;


import com.jjickda.domain.admin.dto.QuestionAddDto;
import com.jjickda.domain.admin.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// 어드민 컨트롤러들은 앞에 /admin이 붙어야 나중에 aop 등을 사용해서 권한 로직 처리에 편함이 생길꺼 같음.
// 확인하고 아래 로직 주석풀고 작업 진행.
@RestController
@RequestMapping("/api")
// @RequestMapping("/api/admin")
public class AdminRestController {
    private final AdminService adminService;

    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }


    // 2가지 문제가 있음.
    // 1. 컨트롤러 return type은 ResponseEntity type으로 던저져야함. 수정 부탁.
    // 2. 이런 체크 로직은 비즈니스 로직에 해당함으로 service 로 이동하는게 좋아보임. service 에서 체크 후 boolean 반환.

    // 특정 ArgumnetResolver에 의해 유효성 검사가 진행되었던 @Valid와 달리, @Validated는 AOP 기반으로 메소드 요청을 인터셉터하여 처리된다.
    // 그래서 아래 @Valid 는 @Validated 로 바꿔주는게 좋다.
    @ApiOperation("Main 등록 API")
    @PostMapping("/regist-main")
    public Boolean registerMain(@Valid QuestionAddDto question) {
        boolean isSuccess = false;
        int successCount = adminService.registMain(question);
        if (successCount == 1) {
            isSuccess = true;
        }
        return isSuccess;
    }


    @PostMapping("/regist-sub")
    public boolean registerSub() {


        return false;
    }

}
