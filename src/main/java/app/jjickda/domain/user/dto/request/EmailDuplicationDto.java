package app.jjickda.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Alias("EmailDuplicationDto")
public class EmailDuplicationDto {

    @Email
    @NotBlank
    private String email;
}
