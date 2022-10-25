package app.jjickda.api.admin.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private int use_status;
    private int subject_seq;
    private String subject_name;
    private Timestamp reg_date;
    private long reg_seq;
    private Timestamp udt_date;
    private long udt_seq;

}
