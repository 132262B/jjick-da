package app.jjickda.api.result.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("examSubjectResultListDto")
public class ExamSubjectResultListDto {

    @ApiModelProperty(value = "과목명")
    private String subjectName;

    @ApiModelProperty(value = "과목 합격 점수")
    private float subjectCutOffScore;

    @ApiModelProperty(value = "내 과목 점수")
    private float subjectScore;

    @ApiModelProperty(value = "합격 유무")
    private String passYn;

    @Builder
    public ExamSubjectResultListDto(String subjectName, float subjectCutOffScore, float subjectScore, String passYn) {
        this.subjectName = subjectName;
        this.subjectCutOffScore = subjectCutOffScore;
        this.subjectScore = subjectScore;
        this.passYn = passYn;
    }
}
