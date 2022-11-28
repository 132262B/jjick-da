package app.jjickda.domain.user.dto.response;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Alias("user")
public class User implements Serializable {

    private long idx;

    private String email;

    private String name;

    private int roleIdx;

    private String roleName;

    @Builder
    public User(long idx, String email, String name, int roleIdx, String roleName) {
        this.idx = idx;
        this.email = email;
        this.name = name;
        this.roleIdx = roleIdx;
        this.roleName = roleName;
    }
}
