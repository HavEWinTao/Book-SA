package com.bit.book.service.impl;

import com.bit.book.entity.UsersRole;
import com.bit.book.mapper.UsersRoleMapper;
import com.bit.book.service.UsersRoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fanhongtao
 * 2022/10/13 15:32
 */
@Service
public class UsersRoleServiceImpl extends ServiceImpl<UsersRoleMapper, UsersRole> implements UsersRoleService {
    @Transactional
    @Override
    public UsersRole getByUserId(Integer userId) {
        return this.getOne(Wrappers.<UsersRole>lambdaQuery().eq(UsersRole::getUserId, userId));
    }
}
