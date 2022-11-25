package app.jjickda.api.choice.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("examListDto")
public class ExamListDto {

    @ApiModelProperty(value = "시험 IDX")
    private long examIdx;

    @ApiModelProperty(value = "시험명")
    private String examName;

}
