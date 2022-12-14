package app.jjickda.api.admin.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("addSubjectDto")
public class AddSubjectDto {

    @NotNull(message = "서브카테고리 선택 필수")
    private long subCategoryIdx;

    @NotBlank(message = "과목명 입력 필수")
    @ApiModelProperty(value = "과목명", example = "소프트웨어 설계", required = true)
    private String subjectName;

    @NotNull
    @Positive
    private float subjectCutOffScore;

    @Max(50)
    @NotNull
    @Positive
    private int subjectQuestionCnt;
}
