package com.jjickda.domain.admin.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private long main_ctg_seq;

    @NotBlank(message = "main_ctg_name 을 작성해주세요")
    private String main_ctg_name;

}
