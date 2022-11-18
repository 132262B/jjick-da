package app.jjickda.api.admin.dto.request;

import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("addQuestionDto")
public class AddQuestionDto {
    @Valid
    @NotNull(message = "문항 옵션을 모두 선택해야 합니다.")
    private QuestionInfoDto questionInfoDto;

    @Valid
    @NotNull(message = "문항은 1개 이상 등록해야 합니다.")
    private List<QuestionsDto> questionsDto;

}
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("questionInfoDto")
class QuestionInfoDto {
    @NotBlank(message = "메인 카테고리를 선택해야 합니다.")
    private long mainCategoryIdx;
    @NotBlank(message = "서브 카테고리를 선택해야 합니다.")
    private long subCategoryIdx;
    @NotBlank(message = "시험 제목을 입력해야 합니다.")
    private String examName;
    @Positive
    @NotBlank
    private int optionCnt;
    @Positive
    @NotBlank
    private int questionCnt;
}
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("questionsDto")
class QuestionsDto {
    @NotBlank(message = "모든 문항에 제목을 입력해야 합니다.")
    private String questionName;
    @Positive
    @NotBlank
    private int questionNumber;
    @Positive
    @NotBlank(message = "모든 문항에 정답을 체크해야 합니다.")
    private int answerNumber;
    @Positive
    @NotBlank(message = "모든 문항에 과목을 선택해야 합니다.")
    private long SubjectIdx;

    private long multiMediaIdx;
}