package com.jjickda.domain.test;


import com.jjickda.global.config.api.ApiResponse;
import com.jjickda.global.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@Api(tags = "각종 테스트를 위해 임시로 제작한 TEST API, 개발 종료과정에서 삭제 예정")
@RestController
@RequestMapping("/test-api")
public class TestController {

    @ApiOperation("그때그때 필요할때 바꿔쓰는 TEST API")
    @PostMapping("/test")
    public ResponseEntity<ApiResponse<HashMap<String,Object>>> realTest(@RequestBody HashMap<String,Object> map) {
        map.put("date", DateUtil.getDate_YYYY_MM_DD());

        return ResponseEntity.ok(new ApiResponse<>(map));
    }

    @ApiOperation("전달받은 JSON DATA를 그대로 반환 하는 API")
    @PostMapping("/data-return")
    public ResponseEntity<ApiResponse<HashMap<String,Object>>> dataReturn(@RequestBody HashMap<String,Object> map) {
        log.debug(String.valueOf(map));
        return ResponseEntity.ok(new ApiResponse<>(map));
    }
}
