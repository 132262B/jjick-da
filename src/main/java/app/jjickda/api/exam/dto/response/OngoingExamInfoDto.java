package app.jjickda.api.exam.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("ongoingExamInfoDto")
public class OngoingExamInfoDto {

    private String subCategoryName;

    private long optionsCnt;

}
