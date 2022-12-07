package com.bit.book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("u_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    private String roleName;
}
