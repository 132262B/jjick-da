package app.jjickda.api.exam.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("questionListDto")
public class QuestionListDto {

    private List<ExamInfoDto> examInfoDto;

    @ApiModelProperty(value = "선지 개수")
    private int optionsCnt;

}
