package app.jjickda.api.exam.repository;

import app.jjickda.api.admin.dto.request.ExamInfo;
import app.jjickda.api.exam.dto.request.ChoiceInfoDto;
import app.jjickda.api.exam.dto.response.ExamInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamRepository {

    // 시험문제 조회
    List<ExamInfoDto> examQuestion(ChoiceInfoDto choiceInfoDto);

}
