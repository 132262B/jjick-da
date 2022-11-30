package app.jjickda.api.statistics.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminDashboardDto {

    private List<NewUsersCountByDate> newUsersCountByDate;

    private long userTotalCount;

    private long examTotalCount;

    private long questionTotalCount;

    private long resultTotalCount;

}