package book.model;

import lombok.Data;

@Data
public class UserModelForList {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String userName;
    private Integer roleId;
}
