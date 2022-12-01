package app.jjickda.api.exam.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Positive;

@Getter
@Setter
@Alias("submitAnswerListDto")
public class SubmitAnswerListDto {

    @ApiModelProperty(value = "문항 IDX", example = "241", required = true)
    @Positive
    private long questionIdx;

    @ApiModelProperty(value = "과목 IDX", example = "114", required = true)
    @Positive
    private long subjectIdx;

    @ApiModelProperty(value = "문제번호", example = "1", required = true)
    @Positive
    private int questionNumber;

    @ApiModelProperty(value = "제출한 정답", example = "3")
    private int inputAnswer;
}
