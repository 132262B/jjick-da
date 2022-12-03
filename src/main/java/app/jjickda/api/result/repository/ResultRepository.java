package app.jjickda.api.result.repository;

import app.jjickda.api.result.dto.response.ExamAllResultDto;
import app.jjickda.api.result.dto.response.ExamSubjectResultListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResultRepository {

    // 과목 결과 조회
    List<ExamSubjectResultListDto> selectExamSubjectResultList(long idx, String token);


    // 시험 전체 결과 조회
    ExamAllResultDto selectExamAllResult(long idx, String token);
}