package book.controller;

import book.entity.UserLog;
import book.entity.UserPrivilege;
import book.service.UserLogService;
import book.utils.ResultBody;
import book.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangwei
 * 2022/09/03 16:07
 */
@RestController
@RequestMapping("/dnd/user/log")
public class UserLogController {

    @Autowired
    UserLogService userLogService;

    /**
     * 查询所有
     *
     * @param session
     * @return
     */
    @GetMapping("/list")
    public ResultBody list(HttpSession session) {
        UserUtils.checkPrivilege(UserPrivilege.PRI_READ, "用户无权限查看数据");
        List<UserLog> list = userLogService.list();
        return ResultBody.success(list.stream().sorted((o1, o2) -> (int) (o2.getActionTime().getTime() - o1.getActionTime().getTime())).collect(Collectors.toList()));
    }

    /**
     * 指定删除
     *
     * @param logId
     * @param session
     * @return
     */
    @DeleteMapping("/list/delete/{logId}")
    public ResultBody delete(@PathVariable("logId") Integer logId, HttpSession session) {
        UserUtils.checkPrivilege(UserPrivilege.PRI_EDIT, "用户无权限删除数据");
        userLogService.deleteLogInfo(logId);
        return ResultBody.success("删除成功");
    }

    /**
     * 分页查询
     *
     * @param session
     * @param
     * @return
     */
    @GetMapping("/listByPage")
    public ResultBody listByPage(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        UserUtils.checkPrivilege(UserPrivilege.PRI_READ, "用户无权限查看数据");
        return userLogService.listByPage(pageNum, pageSize);
    }
}
