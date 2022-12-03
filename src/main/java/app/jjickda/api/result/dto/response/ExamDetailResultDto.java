package app.jjickda.api.result.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("examDetailResultDto")
public class ExamDetailResultDto {

    @ApiModelProperty(value = "과목명")
    private String subjectName;

    @ApiModelProperty(value = "문제번호")
    private int questionNumber;

    @ApiModelProperty(value = "내 정답번호")
    private int inputAnswer;

    @ApiModelProperty(value = "정답번호")
    private int answerNumber;

    @ApiModelProperty(value = "정답유무")
    private String answerYn;

}
