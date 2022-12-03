package app.jjickda.api.admin.dto.response;

import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("GetSubjectDto")
public class GetSubjectDto {
    private String subjectName;
    private long idx;
    private String regName;
    private Date regDate;
}
