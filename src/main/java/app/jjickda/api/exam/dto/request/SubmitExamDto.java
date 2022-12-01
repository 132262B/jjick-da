package app.jjickda.api.exam.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@ToString
@Alias("submitExamDto")
public class SubmitExamDto {

    @ApiModelProperty(hidden = true)
    private long examResultIdx;

    @ApiModelProperty(value = "메인 카테고리 IDX", example = "241", required = true)
    @Positive
    private long mainCategoryIdx;

    @ApiModelProperty(value = "서브 카테고리 IDX", example = "243", required = true)
    @Positive
    private long subCategoryIdx;

    @ApiModelProperty(value = "과목 개수", example = "4", required = true)
    @Positive
    private int subjectCnt;

    @ApiModelProperty(value = "제출 정답", required = true)
    @Valid
    private List<SubmitAnswerListDto> submitAnswerList;

}
