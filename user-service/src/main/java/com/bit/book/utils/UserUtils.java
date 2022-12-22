package com.bit.book.utils;

import com.bit.book.entity.UserLog;
import com.bit.book.entity.Privilege;
import com.bit.book.entity.UserStatus;
import com.bit.book.service.UserLogService;
import com.bit.book.enums.ActionType;
import com.bit.book.exception.BasicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.regex.*;

/**
 * @author fanhongtao
 * 2022/10/13 15:32
 */
@Component
public class UserUtils {
    public static final String sessionStatusKey = "userStatus";
    private static final Pattern roleNamePattern = Pattern.compile("（.+）$");

    private static UserLogService userLogService;

    @Autowired
    public void setUserLogService(UserLogService service) {
        userLogService = service;
    }

    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(sessionStatusKey) != null;
    }

    /**
     * 检查用户是否具有某项权限。
     *
     * @throws BasicException 当用户不具有指定权限时抛出。如果用户具有指定权限，此函数正常返回。
     */
    public static void checkPrivilege(HttpSession session, int privilegeId, String msg) {
        if (!isLoggedIn(session)) {
            throw new BasicException(HttpStatus.UNAUTHORIZED.value(), "登录超时，请重新登录");
        }
        UserStatus status = (UserStatus) session.getAttribute(sessionStatusKey);
        for (Privilege privilege : status.getPrivileges()) {
            if (privilege.getPrivilegeId() != privilegeId) continue;
            return;
        }
        throw new BasicException(HttpStatus.FORBIDDEN.value(), msg);
    }

    // 提供两个重载，可以自由地或者清晰地设定日志记录中的描述内容
    public static void log(HttpSession session, ActionType actionType, String description) {
        if (!isLoggedIn(session)) {
            throw new BasicException(HttpStatus.FORBIDDEN.value(), "用户未登录，无法记录日志");
        }
        UserStatus status = (UserStatus) session.getAttribute(sessionStatusKey);
        Matcher m = roleNamePattern.matcher(status.getRoleName());
        UserLog log = new UserLog();
        log.setUserId(status.getUserId());
        log.setUserName(status.getUserName());
        log.setRoleId(status.getRoleId());
        log.setRoleName(m.replaceAll(""));
        log.setActionTime(new Date());
        log.setActionType(actionType.value());
        log.setDescription(description);
        userLogService.save(log);
    }
}
