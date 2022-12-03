package app.jjickda.api.result.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("examSubjectResultListDto")
public class ExamSubjectResultListDto {

    @ApiModelProperty(value = "과목 이름")
    private String subjectName;

    @ApiModelProperty(value = "과목 합격 점수")
    private float subjectCutOffScore;

    @ApiModelProperty(value = "내 과목 점수")
    private float subjectScore;

    @ApiModelProperty(value = "합격 유무")
    private String passYn;

}
