package app.jjickda.api.exam.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("optionsDto")
public class OptionsDto {

    @ApiModelProperty(value = "IDX")
    private long idx;

    @ApiModelProperty(value = "문항IDX")
    private long questionIdx;

    @ApiModelProperty(value = "선지번호")
    private int optionsNumber;

    @ApiModelProperty(value = "선지내용")
    private String optionsContent;

    @Builder
    public OptionsDto(long idx, long questionIdx, int optionsNumber, String optionsContent) {
        this.idx = idx;
        this.questionIdx = questionIdx;
        this.optionsNumber = optionsNumber;
        this.optionsContent = optionsContent;
    }
}
