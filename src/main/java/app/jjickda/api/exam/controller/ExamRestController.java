package app.jjickda.api.exam.controller;

import app.jjickda.api.exam.dto.request.ChoiceInfoDto;
import app.jjickda.api.exam.dto.response.QuestionListDto;
import app.jjickda.api.exam.service.ExamService;
import app.jjickda.global.config.model.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "시험 페이지 데이터 처리와 관련된 API")
@RestController
@RequestMapping("/api")
public class ExamRestController {

    private final ExamService ExamService;

    public ExamRestController(ExamService ExamService) {
        this.ExamService = ExamService;
    }

    @ApiOperation("문제를 가져오는 API")
    @PostMapping("/exam-question")
    public ResponseEntity<ApiResponse<QuestionListDto>> examQuestion(@RequestBody ChoiceInfoDto choiceInfoDto) {
        return ResponseEntity.ok(new ApiResponse<>(ExamService.examQuestion(choiceInfoDto)));
    }

}
