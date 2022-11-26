package app.jjickda.api.admin.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@Alias("question")
public class Question {

    @ApiModelProperty(hidden = true)
    private long questionIdx;

    @ApiModelProperty(value = "과목IDX", example = "1", required = true)
    private long subjectIdx;

    @ApiModelProperty(value = "멀티미디어IDX", example = "3512")
    private long multiMediaIdx;

    @ApiModelProperty(value = "문항번호", example = "32", required = true)
    private int questionNumber;

    @Length(max = 2048)
    @ApiModelProperty(value = "문항명", example = "3인 이상이 자유롭게 의견을 교환하며 독창적인 아이디어 산출해 내는 방법으로 옳은것은?", required = true)
    private String questionName;

    @ApiModelProperty(value = "정답번호", example = "4", required = true)
    private int answerNumber;

    @Valid
    @ApiModelProperty(value = "시험문항선지", required = true)
    private List<Options> options;


}
