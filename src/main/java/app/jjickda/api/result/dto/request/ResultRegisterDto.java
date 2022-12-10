package app.jjickda.api.result.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Alias("resultRegisterDto")
public class ResultRegisterDto {

    @ApiModelProperty(value = "결과 IDX", example = "12", required = true)
    @Positive
    private long idx;

    @ApiModelProperty(value = "결과 토큰값", example = "33d49b52-4f2b-4d6f-9ab9-a93e267641cd", required = true)
    @NotNull
    private String token;

    @ApiModelProperty(value = "비로그인시 등록 아이디", example = "igor", required = true)
    @NotNull
    @Length(min = 4, max = 30)
    private String unLoginRegId;

}
