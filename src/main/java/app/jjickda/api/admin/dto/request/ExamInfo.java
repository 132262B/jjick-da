package app.jjickda.api.admin.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("examInfo")
public class ExamInfo {

    private long examIdx;

    private long mainCategoryIdx;

    private long subCategoryIdx;

    private String examName;

    private int optionCnt;

    private int questionCnt;

    private float examCutOffScore;

}
