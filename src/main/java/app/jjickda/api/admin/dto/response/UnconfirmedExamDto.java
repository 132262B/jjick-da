package app.jjickda.api.admin.dto.response;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("UnconfirmedExamDto")
public class UnconfirmedExamDto {
    private long idx;
    private String examName;
    private Date regDate;
    private String regName;
    private long regIdx;
}
