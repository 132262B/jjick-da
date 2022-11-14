package app.jjickda.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("user")
public class User implements Serializable {

    private long idx;

    private String email;

    private String name;

    private String userRole;

}
