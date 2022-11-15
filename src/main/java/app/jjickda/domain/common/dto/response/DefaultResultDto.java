package app.jjickda.domain.common.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DefaultResultDto {

    @ApiModelProperty(value = "메시지")
    private String message;

    @ApiModelProperty(value = "성공유무")
    private boolean success;

    @Builder
    public DefaultResultDto(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
