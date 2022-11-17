package app.jjickda.api.exam.controller;

import app.jjickda.api.exam.service.ExamService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "시험 페이지 데이터 처리와 관련된 API")
@RestController
@RequestMapping("/api")
public class ExamRestController {

    private final ExamService examService;

    public ExamRestController(ExamService examService) {
        this.examService = examService;
    }



}
