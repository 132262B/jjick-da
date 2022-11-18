package app.jjickda.api.admin.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}
