package app.jjickda.api.exam.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("resultTokenAndIndex")
public class ResultTokenAndIndex {

    @ApiModelProperty(value = "결과 IDX")
    private long resultIdx;

    @ApiModelProperty(value = "결과 TOKEN")
    private String token;

    @Builder
    public ResultTokenAndIndex(long resultIdx, String token) {
        this.resultIdx = resultIdx;
        this.token = token;
    }
}
