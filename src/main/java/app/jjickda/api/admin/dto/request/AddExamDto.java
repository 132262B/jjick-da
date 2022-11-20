package app.jjickda.api.admin.dto.request;

import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@Alias("addExamDto")
public class AddExamDto {

    private ExamInfo examInfo;

    @Valid
    private List<Question> questions;
}


