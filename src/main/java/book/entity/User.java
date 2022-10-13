package book.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("u_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;
    @TableField(value = "password")
    private String hashedPassword;
}
