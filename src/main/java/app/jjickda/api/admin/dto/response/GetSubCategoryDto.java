package app.jjickda.api.admin.dto.response;

import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("getSubCategoryDto")
public class GetSubCategoryDto {

    private String subCategoryName;
    private long idx;
    private int useStatus;
    private Timestamp regDate;
    private long regIdx;
    private Timestamp udtDate;
    private long udtIdx;
    private String regUserName;
    private String udtUserName;
}
