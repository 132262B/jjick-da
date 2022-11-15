package app.jjickda.api.admin.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddSubjectDto {
    @NotNull(message = "서브카테고리 선택 필수")
    private long subCategoryIdx;
    @NotNull(message = "과목명 입력 필수")
    private String subjectName;
}
