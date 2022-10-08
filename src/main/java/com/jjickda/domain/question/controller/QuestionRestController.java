package com.jjickda.domain.question.controller;

import com.jjickda.domain.question.dto.response.QuestionListDto;
import com.jjickda.domain.question.service.QuestionService;
import com.jjickda.global.config.api.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "시험과목 데이터 처리와 관련된 API.")
@RestController
@RequestMapping("/api")
public class QuestionRestController {

    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ApiOperation("자격증을 뿌려주는 api")
    @PostMapping("/select-certificate")
    public ResponseEntity<ApiResponse<List<QuestionListDto>>> questionSelect() {
        List<QuestionListDto> questionListDto = questionService.questionSelect();
        return ResponseEntity.ok(new ApiResponse<>(questionListDto));
    }

    @ApiOperation("시험회차를 뿌려주는 api")
    @PostMapping("/select-term/{questionSeq}")
    public ResponseEntity<ApiResponse<List<QuestionListDto>>> termSelect(@PathVariable long questionSeq) {
        List<QuestionListDto> questionListDto = questionService.termSelect(questionSeq);
        return ResponseEntity.ok(new ApiResponse<>(questionListDto));
    }

    @ApiOperation("시험과목을 뿌려주는 api")
    @PostMapping("/select-subject/{questionSeq}")
    public ResponseEntity<ApiResponse<List<QuestionListDto>>> subjectShow(@PathVariable long questionSeq) {
        List<QuestionListDto> questionListDto = questionService.subjectShow(questionSeq);
        return ResponseEntity.ok(new ApiResponse<>(questionListDto));
    }

}
