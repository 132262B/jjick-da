package app.jjickda.api.admin.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@Alias("question")
public class Question {

    private long questionIdx;

    private long subjectIdx;

    private long multiMediaIdx;

    private int questionNumber;

    private String questionName;

    private int answerNumber;

    private float score;


    @Valid
    private List<Options> options;


}
