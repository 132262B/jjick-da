package app.jjickda.api.exam.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("ongoingExamInfoDto")
public class OngoingExamInfoDto {

    @ApiModelProperty(value = "진행중인 시험 이름")
    private String subCategoryName;

    @ApiModelProperty(value = "선지 개수")
    private long optionsCnt;


}
