package app.jjickda.api.question.dto.response;


import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("QuestionListDto")
public class QuestionListDto {

    private long questionSeq; // sub_ctg_seq

    private String questionName; // main_ctg_name + sub_ctg_name

    private long termSeq; // exam_seq

    private String termName; // exam_name

    private long subjectSeq; // subject_seq

    private String subjectName; // subject_name

}
