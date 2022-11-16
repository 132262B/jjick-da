package app.jjickda.api.question.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Alias("requirementListDto")
public class RequirementListDto {

    @ApiModelProperty(value = "자격증 IDX", example = "1")
    private long questionIdx;

}
