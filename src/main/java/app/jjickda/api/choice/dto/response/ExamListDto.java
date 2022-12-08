package app.jjickda.api.choice.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("examListDto")
public class ExamListDto {

    @ApiModelProperty(value = "시험 IDX")
    private long examIdx;

    @ApiModelProperty(value = "시험명")
    private String examName;

    @Builder
    public ExamListDto(long examIdx, String examName) {
        this.examIdx = examIdx;
        this.examName = examName;
    }
}
