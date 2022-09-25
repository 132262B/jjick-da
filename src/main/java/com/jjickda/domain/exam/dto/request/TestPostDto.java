package com.jjickda.domain.exam.dto.request;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TestPostDto {


    @NotNull(message = "널안돼")
    private String col1;

    @Size(min = 1, max = 10, message = "길이는 1자리에서 10자리로 해주세요.")
    private String col2;


    @Min(10)
    @Max(30)
    private int col3;
    private String col4;


}
