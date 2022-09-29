package com.jjickda.domain.admin.controller;


import com.jjickda.domain.admin.service.AdminService;
import com.jjickda.domain.question.dto.response.QuestionListDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {
    private final AdminService adminService;

    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }
    @ApiOperation("Main 등록 API")
    @PostMapping("/regist-main")
    public boolean registMain(String main_name) {
        boolean isSuccess = false;
       int successCount = adminService.registMain(main_name);
       if(successCount == 1){
            isSuccess = true;
       }
        return isSuccess;
    }

    @PostMapping("/regist-sub")
    public boolean registSub(){



        return false;
    }

}
