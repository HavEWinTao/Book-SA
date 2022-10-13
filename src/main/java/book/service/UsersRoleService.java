package book.service;

import book.entity.UsersRole;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UsersRoleService extends IService<UsersRole> {
    UsersRole getByUserId(Integer userId);
}
