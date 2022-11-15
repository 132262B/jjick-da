package app.jjickda.domain.role;

import lombok.Getter;

@Getter
public enum Role {

    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");

    private int roleIdx;

    private String roleName;

    Role(int roleIdx, String roleName) {
        this.roleIdx = roleIdx;
        this.roleName = roleName;
    }
}
