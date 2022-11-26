package app.jjickda.api.admin.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;
@Getter
@Setter
@Alias("examInformationDto")
public class ExamInformationDto {

    private int optionsCnt;

    private List<SubjectInformationDto> subjectInformation;
}
