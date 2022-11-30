package app.jjickda.api.statistics.service;

import app.jjickda.api.statistics.dto.response.AdminDashboardDto;
import app.jjickda.api.statistics.dto.response.NewUsersCountByDate;
import app.jjickda.api.statistics.dto.response.UserDashboardDto;
import app.jjickda.api.statistics.repository.StatisticsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("통계 테스트")
@ExtendWith({MockitoExtension.class})
public class StatisticsServiceTest {

    @Mock
    StatisticsRepository statisticsRepository;

    @InjectMocks
    StatisticsService statisticsService;

    @DisplayName("유저 대시보드 통계 테스트")
    @Test
    public void userDashboardTest() {
        when(statisticsRepository.selectUserTotalCount()).thenReturn(2403L);
        when(statisticsRepository.selectExamTotalCount()).thenReturn(52L);
        when(statisticsRepository.selectQuestionTotalCount()).thenReturn(5200L);

        UserDashboardDto userDashboardDto = statisticsService.userDashboard();

        Assertions.assertEquals(userDashboardDto.getUserTotalCount(), 2403L);
        Assertions.assertEquals(userDashboardDto.getExamTotalCount(), 52L);
        Assertions.assertEquals(userDashboardDto.getQuestionTotalCount(), 5200L);

        verify(statisticsRepository, times(1)).selectUserTotalCount();
        verify(statisticsRepository, times(1)).selectExamTotalCount();
        verify(statisticsRepository, times(1)).selectQuestionTotalCount();
    }

    @DisplayName("어드민 대시보드 통계 테스트")
    @Test
    public void adminDashboardTest() {

        List<NewUsersCountByDate> newUsersCountByDateList = new ArrayList<>();

        int[] userCountArray = {23, 11, 2, 3, 45, 6, 4, 25, 23, 52, 12, 62, 23, 14};
        for (int i = 0; i < userCountArray.length; i++) {
            newUsersCountByDateList.add(NewUsersCountByDate.builder()
                    .date(String.format("2022-12-%02d", i + 1))
                    .count(userCountArray[i])
                    .build());
        }


        when(statisticsRepository.selectNewUsersCountByDateList()).thenReturn(newUsersCountByDateList);
        when(statisticsRepository.selectUserTotalCount()).thenReturn(2403L);
        when(statisticsRepository.selectExamTotalCount()).thenReturn(52L);
        when(statisticsRepository.selectQuestionTotalCount()).thenReturn(5200L);

        AdminDashboardDto adminDashboardDto = statisticsService.adminDashboard();

        Assertions.assertEquals(adminDashboardDto.getNewUsersCountByDate(), newUsersCountByDateList);
        Assertions.assertEquals(adminDashboardDto.getUserTotalCount(), 2403L);
        Assertions.assertEquals(adminDashboardDto.getExamTotalCount(), 52L);
        Assertions.assertEquals(adminDashboardDto.getQuestionTotalCount(), 5200L);

        verify(statisticsRepository, times(1)).selectNewUsersCountByDateList();
        verify(statisticsRepository, times(1)).selectUserTotalCount();
        verify(statisticsRepository, times(1)).selectExamTotalCount();
        verify(statisticsRepository, times(1)).selectQuestionTotalCount();
    }

}
