package app.jjickda.api.admin.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubQuestionDto {

    @NotNull(message = "메인카테고리 선택 필수")
    private long MAIN_CATEGORY_IDX;

    private long IDX;

    @NotBlank(message = "sub_ctg_name 을 작성해주세요")
    private String SUB_CATEGORY_NAME;
    private int USE_STATUS;
    private Timestamp REG_DATE;
    private long REG_IDX;
    private Timestamp UDT_DATE;
    private long UDT_IDX;
    private String REG_USER_NAME;
    private String UDT_USER_NAME;
}
