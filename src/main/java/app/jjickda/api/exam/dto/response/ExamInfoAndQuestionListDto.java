package app.jjickda.api.exam.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Slf4j
public class ExamInfoAndQuestionListDto {

    @ApiModelProperty(value = "진행중인 시험 정보")
    private OngoingExamInfoDto ongoingExamInfoDto;

    @ApiModelProperty(value = "진행중인 시험 문항 정보")
    private List<QuestionDto> questionList = new ArrayList<>();

    public void addQuestionList(QuestionDto list) {
        questionList.add(list);
    }
}
