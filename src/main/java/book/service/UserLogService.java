package book.service;

import book.entity.UserLog;
import book.utils.ResultBody;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UserLogService extends IService<UserLog> {

    void deleteLogInfo(Integer logId);

    ResultBody listByPage(int pageNum, int pageSize);
}
