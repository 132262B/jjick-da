package com.jjickda.domain.exam.controller;

import com.jjickda.domain.exam.dto.request.TestPostDto;
import com.jjickda.domain.exam.dto.response.TestDto;
import com.jjickda.domain.exam.service.ExamService;
import com.jjickda.global.config.exception.CustomException;
import com.jjickda.global.config.exception.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Api(tags = "시험 데이터 처리와 관련된 API")
@RestController
@RequestMapping("/api")
public class ExamRestController {

    private final ExamService examService;

    public ExamRestController(ExamService examService) {
        this.examService = examService;
    }




}
