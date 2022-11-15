package app.jjickda.domain.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("emailDuplicationDto")
public class EmailDuplicationDto {

    @Email
    @NotBlank
    @ApiModelProperty(value = "사용자 이메일", example = "user@gmail.com", required = true)
    private String email;
}
