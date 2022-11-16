package app.jjickda.api.question.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("subjectListDto")
public class SubjectListDto {

    @ApiModelProperty(value = "과목 IDX")
    private long subjectIdx;

    @ApiModelProperty(value = "과목명")
    private String subjectName;

}
