package app.jjickda.api.admin.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@Alias("dashBoardDto")
public class DashBoardDto {

    private List<NewUserDto> newUser;

}