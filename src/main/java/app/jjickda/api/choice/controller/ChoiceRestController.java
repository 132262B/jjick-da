package app.jjickda.api.choice.controller;

import app.jjickda.api.choice.dto.response.CertificateListDto;
import app.jjickda.api.choice.dto.response.ExamListDto;
import app.jjickda.api.choice.dto.response.SubjectListDto;
import app.jjickda.api.choice.service.ChoiceService;
import app.jjickda.global.config.model.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "자격증선택 페이지 관련 API.")
@RestController
@RequestMapping("/api/choice/")
public class ChoiceRestController {

    private final ChoiceService ChoiceService;

    public ChoiceRestController(ChoiceService ChoiceService) {
        this.ChoiceService = ChoiceService;
    }

    @ApiOperation(value = "자격증데이터 조회.", notes = "자격증 데이터를 조회(메인카테고리-서브카테고리) 하는 API")
    @GetMapping("/certificate")
    public ResponseEntity<ApiResponse<List<CertificateListDto>>> certificate() {
        return ResponseEntity.ok(new ApiResponse<>(ChoiceService.certificate()));
    }

    @ApiOperation(value = "과목정보 조회", notes = "과목정보를 조회하는 API")
    @GetMapping("/subject/{subIdx}")
    public ResponseEntity<ApiResponse<List<SubjectListDto>>> subject(@PathVariable long subIdx) {
        return ResponseEntity.ok(new ApiResponse<>(ChoiceService.subject(subIdx)));
    }

    @ApiOperation(value = "회차정보 조회", notes = "회차정보를 조회하는 API")
    @GetMapping("/exam/{subIdx}")
    public ResponseEntity<ApiResponse<List<ExamListDto>>> exam(@PathVariable long subIdx) {
        return ResponseEntity.ok(new ApiResponse<>(ChoiceService.exam(subIdx)));
    }

}
