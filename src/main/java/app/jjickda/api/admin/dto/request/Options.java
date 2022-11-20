package app.jjickda.api.admin.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Alias("options")
public class Options {

    private int optionNumber;

    @NotBlank
    private String optionContent;

}
