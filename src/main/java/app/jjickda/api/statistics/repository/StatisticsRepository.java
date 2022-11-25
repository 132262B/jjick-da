package app.jjickda.api.statistics.repository;

import app.jjickda.api.statistics.dto.response.NewUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsRepository {

    // 일일 신규 사용자 리스트 출력
    List<NewUserDto> selectNewUser();

    // 총 이용자 수 조회
    long selectUserTotalCount();

    // 총 시험 수 조회
    long selectExamTotalCount();

    // 총 결과 수 조회
    long selectResulTotaltCount();

    // 총 문항 수 조회
    long selectQuestionTotalCount();
}
