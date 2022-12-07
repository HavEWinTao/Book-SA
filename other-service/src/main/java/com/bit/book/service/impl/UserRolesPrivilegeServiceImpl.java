package com.bit.book.service.impl;

import com.bit.book.entity.Privilege;
import com.bit.book.entity.Role2Privilege;
import com.bit.book.mapper.UserRolesPrivilegeMapper;
import com.bit.book.service.UserPrivilegeService;
import com.bit.book.service.UserRolesPrivilegeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author fanhongtao
 * 2022/10/13 15:32
 */
@Service
public class UserRolesPrivilegeServiceImpl extends ServiceImpl<UserRolesPrivilegeMapper, Role2Privilege> implements UserRolesPrivilegeService {
    @Autowired
    UserPrivilegeService userPrivilegeService;

    @Transactional
    @Override
    public List<Role2Privilege> getByRoleId(Integer roleId) {
        return this.list(Wrappers.<Role2Privilege>lambdaQuery().eq(Role2Privilege::getRoleId, roleId));
    }
    @Transactional
    @Override
    public List<Privilege> getPrivilegesByRoleId(Integer roleId) {
        List<Role2Privilege> list = getByRoleId(roleId);
        List<Privilege> r = new LinkedList<>();
        for (Role2Privilege rp : list) {
            r.add(userPrivilegeService.getById(rp.getPrivilegeId()));
        }
        return r;
    }
}
