package app.jjickda.api.admin.controller;


import app.jjickda.api.admin.dto.SubQuestionDto;
import app.jjickda.api.admin.service.AdminService;
import app.jjickda.api.admin.dto.MainQuestionDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    private final AdminService adminService;

    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ApiOperation("Main_ctg 등록 API")
    @PostMapping("/regist-main")
    public ResponseEntity<Boolean> registerMain(@Validated MainQuestionDto main_question) {
        Boolean isSuccess = adminService.registMain(main_question);

        return ResponseEntity.ok(isSuccess);
    }
    @ApiOperation("Sub_ctg 등록 API")
    @PostMapping("/regist-sub")
    public ResponseEntity<Boolean> registerSub(@Validated SubQuestionDto sub_question) {
        Boolean isSuccess = adminService.registSub(sub_question);

        return ResponseEntity.ok(isSuccess);
    }

    @ApiOperation("서브등록(datalist) 메인등록(list) 에서 쓰일 Main_ctg_getList api")
    @PostMapping("/get-main-list")
    public ResponseEntity<ArrayList<MainQuestionDto>> getMainList() {
        ArrayList<MainQuestionDto> questionList = adminService.getMainList();
        return ResponseEntity.ok(questionList);
    }

    @ApiOperation("문항등록(datalist) 서브등록(list)에 쓰일 Sub_ctg_getList api")
    @PostMapping("/get-sub-list")
    public ResponseEntity<ArrayList<SubQuestionDto>> getSubList() {
        ArrayList<SubQuestionDto> questionList = adminService.getSubList();
        System.out.println(questionList);

        return ResponseEntity.ok(questionList);
    }

}
