package app.jjickda.api.result.repository;

import app.jjickda.api.result.dto.response.ExamAllResultDto;
import app.jjickda.api.result.dto.response.ExamDetailResultDto;
import app.jjickda.api.result.dto.response.ExamSubjectResultListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResultRepository {

    // 과목결과 조회
    List<ExamSubjectResultListDto> selectExamSubjectResultList(long idx, String token);


    // 시험전체 결과 조회
    ExamAllResultDto selectExamAllResult(long idx, String token);

    // 시험결과 상세 조회
    List<ExamDetailResultDto> selectExamDetailResultList(long idx, String token);
}
