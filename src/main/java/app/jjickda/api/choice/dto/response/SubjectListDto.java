package app.jjickda.api.choice.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("subjectListDto")
public class SubjectListDto {

    @ApiModelProperty(value = "과목 IDX")
    private long subjectIdx;

    @ApiModelProperty(value = "과목명")
    private String subjectName;

    @Builder
    public SubjectListDto(long subjectIdx, String subjectName) {
        this.subjectIdx = subjectIdx;
        this.subjectName = subjectName;
    }
}
