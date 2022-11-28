package app.jjickda.api.exam.dto.request;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Alias("choiceInfoDto")
public class ChoiceInfoDto {

    private long mainCtgIdx;

    private long subCtgIdx;

    private List<Long> examIdxArray;

    private List<Long> subjectIdxArray;

}
