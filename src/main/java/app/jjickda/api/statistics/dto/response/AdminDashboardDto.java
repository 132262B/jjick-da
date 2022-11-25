package app.jjickda.api.statistics.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@Alias("dashboardDto")
public class AdminDashboardDto {

    private List<NewUserDto> newUser;

}