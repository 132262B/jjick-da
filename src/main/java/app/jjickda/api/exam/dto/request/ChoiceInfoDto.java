package app.jjickda.api.exam.dto.request;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@ToString
@Alias("choiceInfoDto")
public class ChoiceInfoDto {

    private long mainCtgIdx;

    private long subCtgIdx;

    private List<Long> examIdxArray;

    private List<Long> subjectIdxArray;

}
