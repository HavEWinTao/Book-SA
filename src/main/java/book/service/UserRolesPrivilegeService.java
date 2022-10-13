package book.service;

import book.entity.UserPrivilege;
import book.entity.UserRolesPrivilege;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserRolesPrivilegeService extends IService<UserRolesPrivilege> {
    List<UserRolesPrivilege> getByRoleId(Integer roleId);
    List<UserPrivilege> getPrivilegesByRoleId(Integer roleId);
}
