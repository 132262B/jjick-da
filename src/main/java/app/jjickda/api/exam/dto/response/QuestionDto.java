package app.jjickda.api.exam.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;


@Getter
@Setter
@ToString
@Alias("questionDto")
public class QuestionDto {

    @ApiModelProperty(value = "문항 IDX")
    private long questionIdx;

    @ApiModelProperty(value = "파일 ID")
    private String fileId;

    @ApiModelProperty(value = "문항번호")
    private int questionNumber;

    @ApiModelProperty(value = "과목IDX")
    private long subjectIdx;

    @ApiModelProperty(value = "과목이름")
    private String subjectName;

    @ApiModelProperty(value = "문항명")
    private String questionName;

    @ApiModelProperty(value = "선지 리스트")
    private List<OptionsDto> optionsList;

}