package com.jjickda.domain.admin.controller;


import com.jjickda.domain.admin.dto.QuestionDto;
import com.jjickda.domain.admin.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AdminRestController {
    private final AdminService adminService;

    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }
    @ApiOperation("Main 등록 API")
    @PostMapping("/regist-main")
    public Boolean registMain(@Valid QuestionDto question) {
       boolean isSuccess = false;
       int successCount = adminService.registMain(question);
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
