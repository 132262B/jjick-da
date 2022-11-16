package app.jjickda.api.question.dto.response;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("subjectListDto")
public class SubjectListDto {

    private long subjectIdx;

    private String subjectName;

}
