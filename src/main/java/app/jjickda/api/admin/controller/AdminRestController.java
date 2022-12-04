package app.jjickda.api.admin.controller;


import app.jjickda.api.admin.dto.request.AddExamDto;
import app.jjickda.api.admin.dto.request.AddMainCategoryDto;
import app.jjickda.api.admin.dto.request.AddSubCategoryDto;
import app.jjickda.api.admin.dto.request.AddSubjectDto;
import app.jjickda.api.admin.dto.response.*;
import app.jjickda.api.admin.service.AdminService;
import app.jjickda.domain.common.dto.response.DefaultResultDto;
import app.jjickda.domain.role.Role;
import app.jjickda.global.annotation.LoginCheck;
import app.jjickda.global.config.exception.Type;
import app.jjickda.global.config.model.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "어드민 관련 API")
@RequestMapping("/api/admin")
public class AdminRestController {
    private final AdminService adminService;

    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ApiOperation(value = "메인카테고리 조회.", notes = "메인카테고리 조회할때 쓰는 API.")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @GetMapping("/main/category")
    public ResponseEntity<ApiResponse<List<GetMainCategoryDto>>> getMainCategoryList(@RequestBody SearchDto searchDto) {
        List<GetMainCategoryDto> questionList = adminService.getMainList(searchDto);
        return ResponseEntity.ok(new ApiResponse<>(questionList));
    }

    @ApiOperation(value = "메인카테고리 등록.", notes = "메인카테고리 등록할때 쓰는 API.")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @PostMapping("/main/category")
    public ResponseEntity<ApiResponse<DefaultResultDto>> registerMain(@Validated @RequestBody AddMainCategoryDto addMainCategoryDto) {
        return ResponseEntity.ok(new ApiResponse<>(adminService.registMain(addMainCategoryDto)));
    }

    @ApiOperation(value = "서브카테고리 등록.", notes = "서브카테고리 등록할때 쓰는 API.")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @PostMapping("/sub/category")
    public ResponseEntity<ApiResponse<DefaultResultDto>> registerSub(@Validated @RequestBody AddSubCategoryDto sub_question) {
        return ResponseEntity.ok(new ApiResponse<>(adminService.registSub(sub_question)));
    }

    @ApiOperation(value = "서브카테고리 조회.", notes = "서브카테고리 조회할때 쓰는 API")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @GetMapping("/sub/category")
    public ResponseEntity<ApiResponse<List<GetSubCategoryDto>>> getSubList(@RequestParam String search,
                                                                           @RequestParam String sort) {
        return ResponseEntity.ok(new ApiResponse<>(adminService.selectSubCategoryList(search, sort)));
    }

    @ApiOperation(value = "서브카테고리 상세정보 조회.", notes = "서브카테고리 상세정보 조회할때 쓰는 API")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @GetMapping("/sub/category/detail/{subIdx}")
    public ResponseEntity<ApiResponse<GetSubCategoryDto>> getSubDetail(@PathVariable long subIdx) {
        return ResponseEntity.ok(new ApiResponse<>(adminService.getSubDetail(subIdx)));
    }

    @ApiOperation(value = "메인IDX로 서브카테고리 조회.", notes = "메인IDX를 통해 서브카테고리를 조회할때 쓰는 API")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @GetMapping("/sub/category/{mainIdx}")
    public ResponseEntity<ApiResponse<List<GetSubCategoryDto>>> getSubCategory(@PathVariable long mainIdx) {
        return ResponseEntity.ok(new ApiResponse<>(adminService.selectSubCategoryList(mainIdx)));
    }

    @ApiOperation(value = "서브IDX로 과목리스트 조회.", notes = "서브IDX로 통해 과목리스트 조회할때 쓰는 API")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @GetMapping("/subject/{subIdx}")
    public ResponseEntity<ApiResponse<List<GetSubjectDto>>> getSubjectCategory(@PathVariable long subIdx) {
        return ResponseEntity.ok(new ApiResponse<>(adminService.getSubjectCategory(subIdx)));
    }

    @ApiOperation(value = "과목정보 등록.", notes = "과목정보를 등록할때 쓰는 API.")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @PostMapping("/subject")
    public ResponseEntity<ApiResponse<DefaultResultDto>> addSubject(@Validated @RequestBody AddSubjectDto addSubjectDto) {
        return ResponseEntity.ok(new ApiResponse<>(adminService.registSubject(addSubjectDto)));
    }

    @ApiOperation(value = "문항정보 등록.", notes = "문항정보를 등록할때 쓰는 API.")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @PostMapping("/exam")
    public ResponseEntity<ApiResponse<DefaultResultDto>> addExam(@Validated @RequestBody AddExamDto addExamDto) {
        return ResponseEntity.ok(new ApiResponse<>(adminService.addExam(addExamDto)));
    }

    @ApiOperation(value = "서브IDX로 문항정보 조회.", notes = "서브IDX로 통해 문항정보 조회할때 쓰는 API")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @GetMapping("/exam/{subIdx}")
    public ResponseEntity<ApiResponse<ExamInformationDto>> getExam(@PathVariable long subIdx) {
        return ResponseEntity.ok(new ApiResponse<>(adminService.getExamInformation(subIdx)));
    }

    @ApiOperation(value = "시험 결재정보 조회.", notes = "시험 결재정보 조회할때 쓰는 API")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @GetMapping("/confirm")
    public ResponseEntity<ApiResponse<List<UnconfirmedExamDto>>> getConfirm(@RequestParam(required = false) String search) {
        List<UnconfirmedExamDto> unconfirmedExamDataList = adminService.getUnconfirmedExamData(search);
        return ResponseEntity.ok(new ApiResponse<>(unconfirmedExamDataList));
    }

    @ApiOperation(value = "시험 결재정보 등록.", notes = "시험 결재정보 등록할때 쓰는 API")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @PutMapping("/confirm")
    public ResponseEntity<ApiResponse<DefaultResultDto>> addConfirm(@RequestBody List<Long> examIdx) {
        DefaultResultDto response = adminService.confirmExam(examIdx);
        return ResponseEntity.ok(new ApiResponse<>(response));
    }

}

