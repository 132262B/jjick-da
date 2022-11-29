package app.jjickda.api.statistics.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDashboardDto {

    private long userTotalCount;

    private long examTotalCount;

    private long questionTotalCount;

    private long resultTotalCount;
}
