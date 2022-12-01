package app.jjickda.api.admin.dto.response;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

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
    private Date regDate;
    private long regIdx;
    private Date udtDate;
    private long udtIdx;
    private String regUserName;
    private String udtUserName;
    private String mainCategoryName;
    private int subjectCount;
    private long mainCategoryIdx;
}
