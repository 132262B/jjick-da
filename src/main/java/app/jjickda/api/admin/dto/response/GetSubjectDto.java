package app.jjickda.api.admin.dto.response;

import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("GetSubjectDto")
public class GetSubjectDto {
    private long subCategoryIdx;
    private String subjectName;
    private long idx;
}
