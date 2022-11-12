package app.jjickda.domain.common.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DefaultResultDto {

    private String message;
    private boolean success;

    @Builder
    public DefaultResultDto(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
