package app.jjickda.api.question.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("certificateListDto")
public class CertificateListDto {

    @ApiModelProperty(value = "메인 카테고리 IDX")
    private long mainIdx;

    @ApiModelProperty(value = "서브 카테고리 IDX")
    private long subIdx;

    @ApiModelProperty(value = "메인 카테고리명 + 서브 카테고리명")
    private String certificateName;

}
