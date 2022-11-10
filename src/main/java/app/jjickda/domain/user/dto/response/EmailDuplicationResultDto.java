package app.jjickda.domain.user.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailDuplicationResultDto {

    private String message;
    private boolean success;

    @Builder
    public EmailDuplicationResultDto(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
