package app.jjickda.api.statistics.service;

import app.jjickda.api.statistics.dto.response.AdminDashboardDto;
import app.jjickda.api.statistics.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    // 대시보드 데이터 조회
    public AdminDashboardDto adminDashboard() {
        AdminDashboardDto adminDashboardDto = new AdminDashboardDto();

        adminDashboardDto.setNewUser(statisticsRepository.selectNewUser());


        return adminDashboardDto;
    }
}
