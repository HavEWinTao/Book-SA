package book.vo;

import lombok.Data;

@Data
public class UserReqData {
    private String username;
    private String password;
    private Integer roleId;
}
