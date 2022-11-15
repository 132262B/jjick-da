package app.jjickda.api.admin.dto.response;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetSubjectDto {
    @NotNull(message = "서브카테고리 선택 필수")
    private long subCategoryIdx;
    @NotNull(message = "과목명 입력 필수")
    private String subjectName;
}
