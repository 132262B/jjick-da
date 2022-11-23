package app.jjickda.api.admin.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@Alias("addExamDto")
public class AddExamDto {

    @Valid
    @ApiModelProperty(value = "시험 정보", required = true)
    private ExamInfo examInfo;

    @Valid
    @ApiModelProperty(value = "시험 문항", required = true)
    private List<Question> questions;
}


