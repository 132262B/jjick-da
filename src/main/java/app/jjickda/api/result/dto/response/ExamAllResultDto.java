package app.jjickda.api.result.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("examAllResultDto")
public class ExamAllResultDto {

    @ApiModelProperty(value = "평균 점수")
    private float averageScore;

    @ApiModelProperty(value = "합격 유무")
    private String passYn;
}
