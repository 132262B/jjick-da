package app.jjickda.api.admin.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("addSubCategoryDto")
public class AddSubCategoryDto {

    @NotNull(message = "메인카테고리 선택 필수")
    private long mainCategoryIdx;

    @NotBlank(message = "sub_ctg_name 을 작성해주세요")
    @ApiModelProperty(value = "서브 카테고리 이름", example = "정보처리기사(필기)", required = true)
    private String subCategoryName;

    @NotNull
    @Positive
    private float examCutOffScore;

    @Max(value = 5)
    @ApiModelProperty(value = "선지개수", example = "5", required = true)
    private int optionsCnt;
}
