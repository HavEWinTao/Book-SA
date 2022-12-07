package com.bit.book.mapper;

import com.bit.book.entity.User;
import com.bit.book.model.UserModelForList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author fanhongtao
 * 2022/10/13 15:32
 */
public interface UserMapper extends BaseMapper<User> {
    List<UserModelForList> getList();
}
