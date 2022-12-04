package app.jjickda.api.statistics.service;

import app.jjickda.api.statistics.dto.response.UserDashboardDto;
import app.jjickda.api.statistics.dto.response.AdminDashboardDto;
import app.jjickda.api.statistics.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    // 어드민 대시보드 데이터 조회
    public AdminDashboardDto adminDashboard() {
        AdminDashboardDto adminDashboardDto = new AdminDashboardDto();

        adminDashboardDto.setTwoWeeksNewUsersCountAndDate(statisticsRepository.selectTwoWeeksNewUsersCountAndDateList());
        adminDashboardDto.setTwoWeeksSubmitResultCountAndDate(statisticsRepository.selectTwoWeeksSubmitResultCountAndDateList());

        adminDashboardDto.setTopCountByExamFiveList(statisticsRepository.selectTopCountByExamFiveList());

        adminDashboardDto.setUserTotalCount(statisticsRepository.selectUserTotalCount());
        adminDashboardDto.setExamTotalCount(statisticsRepository.selectExamTotalCount());
        adminDashboardDto.setQuestionTotalCount(statisticsRepository.selectQuestionTotalCount());
        adminDashboardDto.setResultTotalCount(statisticsRepository.selectResulTotaltCount());

        return adminDashboardDto;
    }

    // 유저 대시보드 데이터 조회
    public UserDashboardDto userDashboard() {
        UserDashboardDto userDashboardDto = new UserDashboardDto();

        userDashboardDto.setUserTotalCount(statisticsRepository.selectUserTotalCount());
        userDashboardDto.setExamTotalCount(statisticsRepository.selectExamTotalCount());
        userDashboardDto.setQuestionTotalCount(statisticsRepository.selectQuestionTotalCount());
        userDashboardDto.setResultTotalCount(statisticsRepository.selectResulTotaltCount());

        return userDashboardDto;
    }
}
