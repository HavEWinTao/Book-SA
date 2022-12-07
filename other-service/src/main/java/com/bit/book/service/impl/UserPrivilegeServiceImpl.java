package com.bit.book.service.impl;

import com.bit.book.entity.Privilege;
import com.bit.book.mapper.UserPrivilegeMapper;
import com.bit.book.service.UserPrivilegeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author fanhongtao
 * 2022/10/13 15:32
 */
@Service
public class UserPrivilegeServiceImpl extends ServiceImpl<UserPrivilegeMapper, Privilege> implements UserPrivilegeService {
}
