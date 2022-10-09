package app.jjickda.api.question.repository;

import app.jjickda.api.question.dto.response.QuestionListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionRepository {
    List<QuestionListDto> questionSelect();

    List<QuestionListDto> termSelect(long questionSeq);

    List<QuestionListDto> subjectShow(long questionSeq);
}
