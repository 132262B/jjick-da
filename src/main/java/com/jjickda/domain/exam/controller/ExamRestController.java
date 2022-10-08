package com.jjickda.domain.exam.controller;

import com.jjickda.domain.exam.service.ExamService;
import com.jjickda.global.config.api.ApiResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@Api(tags = "시험 데이터 처리와 관련된 API")
@RestController
@RequestMapping("/api")
public class ExamRestController {

    private final ExamService examService;

    public ExamRestController(ExamService examService) {
        this.examService = examService;
    }



}
