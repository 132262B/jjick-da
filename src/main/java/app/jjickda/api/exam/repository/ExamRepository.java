package app.jjickda.api.exam.repository;

import app.jjickda.api.exam.dto.request.ChoiceInfoDto;
import app.jjickda.api.exam.dto.request.SubmitExamDto;
import app.jjickda.api.exam.dto.response.OngoingExamInfoDto;
import app.jjickda.api.exam.dto.response.OptionsDto;
import app.jjickda.api.exam.dto.response.QuestionDto;
import app.jjickda.domain.user.dto.response.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamRepository {

    // 시험문제 조회
    List<QuestionDto> selectQuestionList(ChoiceInfoDto choiceInfoDto, long subjectIdx);

    // 시험과목 테이블에서 과목 별 문항 개수 조회
    long selectSubjectQuestionCnt(long subjectIdx);

    // 선지 정보 조회
    List<OptionsDto> selectOptionsList(List<Long> questionIdxList);

    // 진행중인 시험 정보 조회
    OngoingExamInfoDto selectOngoingExamInfo(ChoiceInfoDto choiceInfoDto);

    // 시험 전체 결과 등록
    void insertExamAllResult(SubmitExamDto submitExamDto, User user, String uuid);

    // 시험 문항 결과 등록
    void insertExamQuestionResult(SubmitExamDto submitExamDto);

    // 시험 과목별 결과 등록
    void insertExamSubjectResult(SubmitExamDto submitExamDto);

    // 시험 전체 결과 업데이트(평균점수,합격유무)
    void updateExamAllResult(long examResultIdx, long subCategoryIdx);
}
