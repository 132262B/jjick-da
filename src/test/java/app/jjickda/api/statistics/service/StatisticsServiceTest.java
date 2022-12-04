package app.jjickda.api.statistics.service;

import app.jjickda.api.statistics.dto.response.AdminDashboardDto;
import app.jjickda.api.statistics.dto.response.CountAndDataDto;
import app.jjickda.api.statistics.dto.response.CountAndDateDto;
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
        when(statisticsRepository.selectResulTotaltCount()).thenReturn(12322L);

        UserDashboardDto userDashboardDto = statisticsService.userDashboard();

        Assertions.assertEquals(userDashboardDto.getUserTotalCount(), 2403L);
        Assertions.assertEquals(userDashboardDto.getExamTotalCount(), 52L);
        Assertions.assertEquals(userDashboardDto.getQuestionTotalCount(), 5200L);
        Assertions.assertEquals(userDashboardDto.getResultTotalCount(), 12322L);

        verify(statisticsRepository, times(1)).selectUserTotalCount();
        verify(statisticsRepository, times(1)).selectExamTotalCount();
        verify(statisticsRepository, times(1)).selectQuestionTotalCount();
        verify(statisticsRepository, times(1)).selectResulTotaltCount();
    }

    @DisplayName("어드민 대시보드 통계 테스트")
    @Test
    public void adminDashboardTest() {

        List<CountAndDateDto> countAndDateList = new ArrayList<>();

        int[] userCountArray = {23, 11, 2, 3, 45, 6, 4, 25, 23, 52, 12, 62, 23, 14};
        for (int i = 0; i < userCountArray.length; i++) {
            countAndDateList.add(CountAndDateDto.builder()
                    .date(String.format("2022-12-%02d", i + 1))
                    .count(userCountArray[i])
                    .build());
        }

        List<CountAndDataDto> countAndDataList = new ArrayList<>();
        countAndDataList.add(CountAndDataDto.builder().data("정보처리기사(필기)").count(1985).build());
        countAndDataList.add(CountAndDataDto.builder().data("정보보안기사(필기)").count(634).build());
        countAndDataList.add(CountAndDataDto.builder().data("정보처리기능사(필기)").count(554).build());
        countAndDataList.add(CountAndDataDto.builder().data("전자기기기사(필기)").count(634).build());
        countAndDataList.add(CountAndDataDto.builder().data("전기기사(필기)").count(853).build());


        when(statisticsRepository.selectTwoWeeksNewUsersCountAndDateList()).thenReturn(countAndDateList);
        when(statisticsRepository.selectTwoWeeksSubmitResultCountAndDateList()).thenReturn(countAndDateList);

        when(statisticsRepository.selectTopCountByExamFiveList()).thenReturn(countAndDataList);

        when(statisticsRepository.selectUserTotalCount()).thenReturn(2403L);
        when(statisticsRepository.selectExamTotalCount()).thenReturn(52L);
        when(statisticsRepository.selectQuestionTotalCount()).thenReturn(5200L);
        when(statisticsRepository.selectResulTotaltCount()).thenReturn(12322L);

        AdminDashboardDto adminDashboardDto = statisticsService.adminDashboard();

        Assertions.assertEquals(adminDashboardDto.getTwoWeeksNewUsersCountAndDate(), countAndDateList);
        Assertions.assertEquals(adminDashboardDto.getTwoWeeksSubmitResultCountAndDate(), countAndDateList);

        Assertions.assertEquals(adminDashboardDto.getTopCountByExamFiveList(), countAndDataList);

        Assertions.assertEquals(adminDashboardDto.getUserTotalCount(), 2403L);
        Assertions.assertEquals(adminDashboardDto.getExamTotalCount(), 52L);
        Assertions.assertEquals(adminDashboardDto.getQuestionTotalCount(), 5200L);
        Assertions.assertEquals(adminDashboardDto.getResultTotalCount(), 12322L);


        verify(statisticsRepository, times(1)).selectTwoWeeksNewUsersCountAndDateList();
        verify(statisticsRepository, times(1)).selectTwoWeeksSubmitResultCountAndDateList();

        verify(statisticsRepository, times(1)).selectTopCountByExamFiveList();

        verify(statisticsRepository, times(1)).selectUserTotalCount();
        verify(statisticsRepository, times(1)).selectExamTotalCount();
        verify(statisticsRepository, times(1)).selectQuestionTotalCount();
        verify(statisticsRepository, times(1)).selectResulTotaltCount();
    }

}
