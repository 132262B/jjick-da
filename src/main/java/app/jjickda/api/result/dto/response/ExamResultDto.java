package app.jjickda.api.result.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ExamResultDto {

    @ApiModelProperty(value = "시험 과목별 결과")
    List<ExamSubjectResultListDto> examSubjectResultList;

    @ApiModelProperty(value = "시험 전체 결과")
    ExamAllResultDto examAllResultDto;
}
