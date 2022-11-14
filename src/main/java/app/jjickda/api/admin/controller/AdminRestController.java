package app.jjickda.api.admin.controller;


import app.jjickda.api.admin.dto.request.AddSubCategoryDto;
import app.jjickda.api.admin.dto.response.GetMainCategoryDto;
import app.jjickda.api.admin.dto.response.GetSubCategoryDto;
import app.jjickda.api.admin.service.AdminService;
import app.jjickda.api.admin.dto.request.AddMainCategoryDto;
import app.jjickda.domain.common.dto.response.DefaultResultDto;
import app.jjickda.global.config.model.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    private final AdminService adminService;

    public AdminRestController(AdminService adminService) {
            this.adminService = adminService;
        }

        @ApiOperation("메인카테고리 등록 API")
        @PostMapping("/regist-main")
        public ResponseEntity<ApiResponse<DefaultResultDto>> registerMain(@Validated AddMainCategoryDto main_question) {
            return ResponseEntity.ok(new ApiResponse<>(adminService.registMain(main_question)));
    }

    @ApiOperation("서브카테고리 등록 API")
    @PostMapping("/regist-sub")
    public ResponseEntity<ApiResponse<DefaultResultDto>> registerSub(@Validated AddSubCategoryDto sub_question) {
        return ResponseEntity.ok(new ApiResponse<>(adminService.registSub(sub_question)));
    }

    @ApiOperation("서브등록(datalist) 메인등록(list) 에서 쓰일 Main_ctg_getList api")
    @PostMapping("/get-main-list")
    public ResponseEntity<ApiResponse<List<GetMainCategoryDto>>> getMainList() {
        List<GetMainCategoryDto> questionList = adminService.getMainList();
        return ResponseEntity.ok(new ApiResponse<>(questionList));
    }

    @ApiOperation("문항등록(datalist) 에 쓰일 서브 카테고리 리스트 API")
    @PostMapping("/get-sub-list")
    public ResponseEntity<ApiResponse<List<GetSubCategoryDto>>> getSubList() {
        List<GetSubCategoryDto> questionList = adminService.getSubList();
        return ResponseEntity.ok(new ApiResponse<>(questionList));
    }

}
