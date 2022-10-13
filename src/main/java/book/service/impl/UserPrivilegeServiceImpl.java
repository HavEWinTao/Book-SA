package book.service.impl;

import book.entity.UserPrivilege;
import book.mapper.UserPrivilegeMapper;
import book.service.UserPrivilegeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserPrivilegeServiceImpl extends ServiceImpl<UserPrivilegeMapper, UserPrivilege> implements UserPrivilegeService {
}
