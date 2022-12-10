package app.jjickda.api.result.dto.response;

import app.jjickda.api.exam.dto.response.OptionsDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@Alias("examDetailResultDto")
public class ExamDetailResultDto {

    @ApiModelProperty(value = "문항IDX")
    private long questionIdx;

    @ApiModelProperty(value = "문항명")
    private String questionName;

    @ApiModelProperty(value = "파일명")
    private String fileId;

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

    @ApiModelProperty(value = "선지 리스트")
    private List<OptionsDto> optionsList;

    @Builder
    public ExamDetailResultDto(long questionIdx, String questionName, String fileId, String subjectName, int questionNumber, int inputAnswer, int answerNumber, String answerYn) {
        this.questionIdx = questionIdx;
        this.questionName = questionName;
        this.fileId = fileId;
        this.subjectName = subjectName;
        this.questionNumber = questionNumber;
        this.inputAnswer = inputAnswer;
        this.answerNumber = answerNumber;
        this.answerYn = answerYn;
    }
}
