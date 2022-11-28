package app.jjickda.api.exam.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("examInfoDto")
public class ExamInfoDto {

    @ApiModelProperty(value = "문항 IDX")
    private long questionIdx;

    @ApiModelProperty(value = "파일 ID")
    private String fileId;

    @ApiModelProperty(value = "문항번호")
    private int questionNumber;

    @ApiModelProperty(value = "문항명")
    private String questionName;

}
