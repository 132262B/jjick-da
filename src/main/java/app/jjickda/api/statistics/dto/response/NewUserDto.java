package app.jjickda.api.statistics.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("newUserDto")
public class NewUserDto {

    private String date;

    private int count;
}