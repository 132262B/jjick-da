package app.jjickda.domain.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Alias("loginDto")
public class LoginDto {

    @Email
    @NotBlank
    @ApiModelProperty(value = "사용자 이메일", example = "user@gmail.com", required = true)
    private String email;

    @NotBlank
    @Length(min = 64, max = 65, message = "패스워드가 SHA-256 형태가 아닙니다.")
    @ApiModelProperty(value = "사용자 비밀번호(SHA256)",
            example = "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4",
            required = true)
    private String password;

    @Builder
    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
