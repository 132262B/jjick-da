package app.jjickda.api.statistics.repository;

import app.jjickda.api.statistics.dto.response.CountAndDataDto;
import app.jjickda.api.statistics.dto.response.CountAndDateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsRepository {

    // 2주 신규 사용자 리스트 조회
    List<CountAndDateDto> selectTwoWeeksNewUsersCountAndDateList();

    // 2주 결과제출 리스트 조회
    List<CountAndDateDto> selectTwoWeeksSubmitResultCountAndDateList();

    // 총 이용자 수 조회
    long selectUserTotalCount();

    // 총 시험 수 조회
    long selectExamTotalCount();

    // 총 결과 수 조회
    long selectResulTotaltCount();

    // 총 문항 수 조회
    long selectQuestionTotalCount();

    // 2주 결과제출 상위 5개 리스트 조회
    List<CountAndDataDto> selectTopCountByExamFiveList();
}
