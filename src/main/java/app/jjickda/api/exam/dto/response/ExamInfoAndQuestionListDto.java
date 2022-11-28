package app.jjickda.api.exam.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Slf4j
public class ExamInfoAndQuestionListDto {

    private List<QuestionDto> questionList = new ArrayList<>();

    public void addQuestionList(QuestionDto list) {
        questionList.add(list);
    }
}
