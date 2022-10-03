package com.jjickda.domain.admin.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MainQuestionDto {

    private long main_ctg_seq;

    @NotBlank(message = "main_ctg_name 을 작성해주세요")
    private String main_ctg_name;
    private int use_status;
    private Timestamp reg_date;
    private long reg_seq;
    private Timestamp udt_date;
    private long udt_seq;
}
