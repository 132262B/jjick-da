package app.jjickda.api.admin.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Getter
@Setter
@Alias("newUserDto")
public class NewUserDto {

    private String date;

    private int count;
}