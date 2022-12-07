package app.jjickda.api.choice.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("certificateListDto")
public class CertificateListDto {

    @ApiModelProperty(value = "메인 카테고리 IDX")
    private long mainIdx;

    @ApiModelProperty(value = "서브 카테고리 IDX")
    private long subIdx;

    @ApiModelProperty(value = "메인 카테고리명 + 서브 카테고리명")
    private String certificateName;

    @Builder
    public CertificateListDto(long mainIdx, long subIdx, String certificateName) {
        this.mainIdx = mainIdx;
        this.subIdx = subIdx;
        this.certificateName = certificateName;
    }
}
