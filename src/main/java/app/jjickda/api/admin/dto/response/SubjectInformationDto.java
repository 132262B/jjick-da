package app.jjickda.api.admin.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("subjectInformationDto")
public class SubjectInformationDto {

    private long subjectIdx;

    private String subjectName;

    private int subjectQuestionCnt;
}
