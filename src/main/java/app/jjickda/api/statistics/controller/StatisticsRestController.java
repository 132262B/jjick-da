package app.jjickda.api.statistics.controller;

import app.jjickda.api.statistics.dto.response.AdminDashboardDto;
import app.jjickda.api.statistics.dto.response.UserDashboardDto;
import app.jjickda.api.statistics.service.StatisticsService;
import app.jjickda.global.config.enumerated.Role;
import app.jjickda.global.annotation.LoginCheck;
import app.jjickda.global.config.enumerated.Type;
import app.jjickda.global.config.model.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "통계관련 API")
@RestController
@RequestMapping("/api/statistics/")
public class StatisticsRestController {

    private final StatisticsService statisticsService;

    public StatisticsRestController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @ApiOperation(value = "어드민 대시보드(통계) 데이터 조회", notes = "어드민 메인페이지(대시보드) 사용자 통계데이터 출력용 API")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @GetMapping("/admin-dashboard")
    public ResponseEntity<ApiResponse<AdminDashboardDto>> AdminDashboard() {
        return ResponseEntity.ok(new ApiResponse<>(statisticsService.adminDashboard()));
    }

    @ApiOperation(value = "유저 대시보드(통계) 데이터 조회", notes = "메인페이지 사용자 통계데이터 출력용 API")
    @GetMapping("/user-dashboard")
    public ResponseEntity<ApiResponse<UserDashboardDto>> UserDashboard() {
        return ResponseEntity.ok(new ApiResponse<>(statisticsService.userDashboard()));
    }

}
