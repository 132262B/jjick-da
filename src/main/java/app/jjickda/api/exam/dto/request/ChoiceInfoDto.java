package app.jjickda.api.exam.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Alias("choiceInfoDto")
public class ChoiceInfoDto {

    @ApiModelProperty(value = "메인 카테고리 IDX", example = "12", required = true)
    @Positive
    private long mainCtgIdx;

    @ApiModelProperty(value = "서브 카테고리 IDX", example = "24", required = true)
    @Positive
    private long subCtgIdx;

    @ApiModelProperty(value = "시험 IDX (배열)", example = "1,2,3,4", required = true)
    @Size(min = 1, message = "하나 이상의 시험을 선택하셔야합니다.")
    private long[] examIdxArray;

    @ApiModelProperty(value = "과목 IDX (배열)", example = "1,2,3,4", required = true)
    @Size(min = 1, message = "하나 이상의 과목을 선택하셔야합니다.")
    private long[] subjectIdxArray;

}
