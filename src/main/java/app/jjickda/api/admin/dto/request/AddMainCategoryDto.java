package app.jjickda.api.admin.dto.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("addMainCategoryDto")
public class AddMainCategoryDto {

    @NotBlank(message = "MAIN_CATEGORY_NAME 을 작성해주세요")
    @ApiModelProperty(value = "메인 카테고리 이름", example = "한국산업인력공단-기능사", required = true)
    private String mainCategoryName;



}
