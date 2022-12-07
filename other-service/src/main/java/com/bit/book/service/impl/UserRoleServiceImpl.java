package com.bit.book.service.impl;

import com.bit.book.entity.Role;
import com.bit.book.mapper.UserRoleMapper;
import com.bit.book.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author fanhongtao
 * 2022/10/13 15:32
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, Role> implements UserRoleService {
}
