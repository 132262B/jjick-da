package app.jjickda.api.exam.controller;

import app.jjickda.api.exam.dto.request.ChoiceInfoDto;
import app.jjickda.api.exam.dto.request.SubmitExamDto;
import app.jjickda.api.exam.dto.response.ExamInfoAndQuestionListDto;
import app.jjickda.api.exam.dto.response.ResultTokenAndIndex;
import app.jjickda.api.exam.service.ExamService;
import app.jjickda.global.config.model.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "시험시작 및 결과제출 관련 API")
@RestController
@RequestMapping("/api/exam/")
public class ExamRestController {

    private final ExamService ExamService;

    public ExamRestController(ExamService ExamService) {
        this.ExamService = ExamService;
    }

    @ApiOperation(value = "시험 시작시 문제 조회", notes = "시험 시작 후 문제를 조회할때 쓰는 API")
    @PostMapping("/start")
    public ResponseEntity<ApiResponse<ExamInfoAndQuestionListDto>> start(@Validated @RequestBody ChoiceInfoDto choiceInfoDto) {
        return ResponseEntity.ok(new ApiResponse<>(ExamService.start(choiceInfoDto)));
    }

    @ApiOperation(value = "시험 문제 제출", notes = "문제를 푼 뒤 제출할때 쓰는 API")
    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<ResultTokenAndIndex>> submit(@Validated @RequestBody SubmitExamDto submitExamDto) {
        return ResponseEntity.ok(new ApiResponse<>(ExamService.submit(submitExamDto)));
    }


}
