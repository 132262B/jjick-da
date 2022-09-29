package com.jjickda.domain.question.controller;

import com.jjickda.domain.question.dto.response.QuestionListDto;
import com.jjickda.domain.question.service.QuestionService;
import com.jjickda.global.config.api.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "시험과목 데이터 처리와 관련된 API.")
@RestController
@RequestMapping("/api")
public class QuestionRestController {

    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // 주소를 select-question 이런 형태로 작성하는게 좀 통일성 있어보이고 좋아보임.
    @ApiOperation("과목을 뿌려주는 api")
    @PostMapping("/question-select")
    public ResponseEntity<List<QuestionListDto>> questionSelect() {
        List<QuestionListDto> questionListDto = questionService.questionSelect();
        return ResponseEntity.ok(questionListDto);
    }

    // 위 참조
    @ApiOperation("시험회차를 뿌려주는 api")
    @PostMapping("/term-select/{questionSeq}")
    public ResponseEntity<List<QuestionListDto>> termSelect(@PathVariable long questionSeq) {
        List<QuestionListDto> questionListDto = questionService.termSelect(questionSeq);
        return ResponseEntity.ok(questionListDto);
    }


    // 공통 ResponseEntity 이 방식으로 사용할 예정
    @ApiOperation("예시")
    @PostMapping("/term-selects/{questionSeq}")
    public ResponseEntity<ApiResponse<List<QuestionListDto>>> termSelects(@PathVariable long questionSeq) {
        List<QuestionListDto> questionListDto = questionService.termSelect(questionSeq);
        return ResponseEntity.ok(new ApiResponse<>(questionListDto));
    }

}
