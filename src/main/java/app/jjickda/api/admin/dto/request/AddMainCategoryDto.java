package app.jjickda.api.admin.dto.request;


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
    private String mainCategoryName;



}
