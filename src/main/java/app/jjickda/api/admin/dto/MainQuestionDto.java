package app.jjickda.api.admin.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MainQuestionDto {
    @NotBlank(message = "MAIN_CATEGORY_NAME 을 작성해주세요")
    private String MAIN_CATEGORY_NAME;

    private long IDX;
    private int USE_STATUS;
    private String REG_USER_NAME;
    private String UDT_USER_NAME;
    private Timestamp REG_DATE;
    private long REG_IDX;
    private Timestamp UDT_DATE;
    private long UDT_IDX;
}
