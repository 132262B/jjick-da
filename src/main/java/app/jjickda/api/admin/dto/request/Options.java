package app.jjickda.api.admin.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Alias("options")
public class Options {

    @ApiModelProperty(value = "선지번호", example = "4", required = true)
    private int optionNumber;

    @NotBlank
    @Length(max = 500)
    @ApiModelProperty(value = "선지내용", example = "브레인스토밍", required = true)
    private String optionContent;

}
