package com.jjickda.domain.exam.controller;

import com.jjickda.domain.exam.dto.response.TestDto;
import com.jjickda.domain.exam.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExamRestController {

    private final ExamService examService;

    public ExamRestController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/test")
    public ResponseEntity<TestDto> test() {
        TestDto testDto = examService.examService();
        return ResponseEntity.ok(testDto);
    }


    // 햐호호

}
