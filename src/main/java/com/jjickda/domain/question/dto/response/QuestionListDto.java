package com.jjickda.domain.question.dto.response;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionListDto {
    private long questionSeq; // sub_ctg_seq
    private String questionName; // main_ctg_name + sub_ctg_name
    private String termName; // exam_name

}
