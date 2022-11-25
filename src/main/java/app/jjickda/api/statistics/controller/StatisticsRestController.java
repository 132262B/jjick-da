package app.jjickda.api.statistics.controller;

import app.jjickda.api.statistics.dto.response.AdminDashboardDto;
import app.jjickda.api.statistics.service.StatisticsService;
import app.jjickda.domain.role.Role;
import app.jjickda.global.annotation.LoginCheck;
import app.jjickda.global.config.exception.Type;
import app.jjickda.global.config.model.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics/")
public class StatisticsRestController {

    private final StatisticsService statisticsService;

    public StatisticsRestController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @ApiOperation("대시보드(통계) 데이터 출력 API")
    @LoginCheck(auth = Role.ADMIN, type = Type.API)
    @GetMapping("/admin-dashboard")
    public ResponseEntity<ApiResponse<AdminDashboardDto>> dashboard() {
        return ResponseEntity.ok(new ApiResponse<>(statisticsService.adminDashboard()));
    }


}
