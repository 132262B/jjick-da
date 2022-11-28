package app.jjickda.api.exam.repository;

import app.jjickda.api.exam.dto.request.ChoiceInfoDto;
import app.jjickda.api.exam.dto.response.OptionsDto;
import app.jjickda.api.exam.dto.response.QuestionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamRepository {

    // 시험문제 조회
    List<QuestionDto> selectQuestionList(ChoiceInfoDto choiceInfoDto, long subjectIdx);

    // 시험과목 테이블에서 과목 별 문항 개수 조회
    long selectSubjectQuestionCnt(long subjectIdx);

    List<OptionsDto> selectOptionsList(List<QuestionDto> questionListDto);
}
