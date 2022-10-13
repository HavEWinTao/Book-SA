package book.mapper;

import book.entity.User;
import book.model.UserModelForList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<UserModelForList> getList();
}
