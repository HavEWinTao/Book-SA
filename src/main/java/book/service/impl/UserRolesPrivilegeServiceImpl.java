package book.service.impl;

import book.entity.UserPrivilege;
import book.entity.UserRolesPrivilege;
import book.mapper.UserRolesPrivilegeMapper;
import book.service.UserPrivilegeService;
import book.service.UserRolesPrivilegeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserRolesPrivilegeServiceImpl extends ServiceImpl<UserRolesPrivilegeMapper, UserRolesPrivilege> implements UserRolesPrivilegeService {
    @Autowired
    UserPrivilegeService userPrivilegeService;

    @Transactional
    @Override
    public List<UserRolesPrivilege> getByRoleId(Integer roleId) {
        return this.list(Wrappers.<UserRolesPrivilege>lambdaQuery().eq(UserRolesPrivilege::getRoleId, roleId));
    }
    @Transactional
    @Override
    public List<UserPrivilege> getPrivilegesByRoleId(Integer roleId) {
        List<UserRolesPrivilege> list = getByRoleId(roleId);
        List<UserPrivilege> r = new LinkedList<>();
        for (UserRolesPrivilege rp : list) {
            r.add(userPrivilegeService.getById(rp.getPrivilegeId()));
        }
        return r;
    }
}
