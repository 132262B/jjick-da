package app.jjickda.api.result.controller;

import app.jjickda.api.result.dto.response.ExamDetailResultDto;
import app.jjickda.api.result.dto.response.ExamResultDto;
import app.jjickda.api.result.service.ResultService;
import app.jjickda.global.config.model.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "시험종료 및 결과조회 관련 API")
@RestController
@RequestMapping("/api/result")
public class ResultRestController {

    private final ResultService resultService;

    public ResultRestController(ResultService resultService) {
        this.resultService = resultService;
    }

    @ApiOperation(value = "시험 종료후 전체결과 조회", notes = "시험 종료후 전체결과 조회할때 쓰는 API")
    @GetMapping
    public ResponseEntity<ApiResponse<ExamResultDto>> result(@RequestParam long idx,
                                                             @RequestParam String token) {

        return ResponseEntity.ok(new ApiResponse<>(resultService.result(idx, token)));
    }

    @ApiOperation(value = "시험 종료후 전체결과 상세조회", notes = "시험 종료후 전체결과 상세조회할때 쓰는 API")
    @GetMapping("detail")
    public ResponseEntity<ApiResponse<List<ExamDetailResultDto>>> resultDetail(@RequestParam long idx,
                                                                                @RequestParam String token) {

        return ResponseEntity.ok(new ApiResponse<>(resultService.resultDetail(idx, token)));
    }

}
