package app.jjickda.api.exam.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {

    private int column1;
    private String column2;
    private String column3;
    private Date column4;
}
