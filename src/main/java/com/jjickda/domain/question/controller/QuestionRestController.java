package com.jjickda.domain.question.controller;

import com.jjickda.domain.question.dto.response.QuestionListDto;
import com.jjickda.domain.question.service.QuestionService;
import com.jjickda.global.config.api.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "시험과목 데이터 처리와 관련된 API.")
@RestController
@RequestMapping("/api")
public class QuestionRestController {

    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ApiOperation("시험과목을 뿌려주는 api")
    @PostMapping("/question-select")
    public ResponseEntity<List<QuestionListDto>> questionSelect() {
        List<QuestionListDto> questionListDto = questionService.questionSelect();
        return ResponseEntity.ok(questionListDto);
    }

}
