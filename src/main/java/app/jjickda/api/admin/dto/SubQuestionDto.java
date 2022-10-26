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
    private long main_ctg_seq;

    private long sub_ctg_seq;

    @NotBlank(message = "sub_ctg_name 을 작성해주세요")
    private String sub_ctg_name;
    private int use_status;
    private Timestamp reg_date;
    private long reg_seq;
    private Timestamp udt_date;
    private long udt_seq;
    private String reg_user_name;
    private String udt_user_name;
}
