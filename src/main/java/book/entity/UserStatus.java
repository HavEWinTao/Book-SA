package book.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserStatus implements Serializable {
    private Integer userId;
    private String userName;
    private Integer roleId;
    private String roleName;
    private UserPrivilege[] privileges;
}
