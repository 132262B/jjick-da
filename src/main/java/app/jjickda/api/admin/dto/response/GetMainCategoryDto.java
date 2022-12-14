package app.jjickda.api.admin.dto.response;


import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("getMainCategoryDto")
public class GetMainCategoryDto {

    @NotBlank(message = "MAIN_CATEGORY_NAME 을 작성해주세요")
    private String mainCategoryName;

    private long idx;

    private int useStatus;

    private String regUserName;

    private String udtUserName;

    private Date regDate;

    private long regIdx;

    private Date udtDate;

    private long udtIdx;
}
