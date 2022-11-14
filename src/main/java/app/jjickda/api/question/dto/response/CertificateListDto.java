package app.jjickda.api.question.dto.response;


import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("certificateListDto")
public class CertificateListDto {

    private long questionIdx; // 서브 카테고리 IDX

    private String questionName; // 메인 카테고리명 + 서브 카테고리명

}
