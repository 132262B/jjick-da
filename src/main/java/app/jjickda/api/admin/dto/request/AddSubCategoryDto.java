package app.jjickda.api.admin.dto.request;

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
    private String subCategoryName;
}
