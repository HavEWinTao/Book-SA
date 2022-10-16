package book.controller;

import book.entity.*;
import book.model.UserModelForList;
import book.vo.UserReqData;
import book.enums.ActionType;
import book.utils.ResultBody;
import book.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static book.utils.UserUtils.sessionStatusKey;

@RestController
@RequestMapping("/book/user")
public class UserController {
    @PostMapping("/login")
    public ResultBody login(@RequestBody UserReqData vo, HttpSession session) {
        //临时用户
        UserStatus status = new UserStatus();
        status.setUserId(1);
        status.setUserName("wjk");
        status.setRoleId(1);
        status.setRoleName("123");
        UserPrivilege[] privilegeArr=new UserPrivilege[2];
        privilegeArr[0] = new UserPrivilege();
        privilegeArr[0].setPrivilegeId(UserPrivilege.PRI_READ);
        privilegeArr[0].setPrivilegeName("PRI_READ");
        privilegeArr[1] = new UserPrivilege();
        privilegeArr[1].setPrivilegeId(UserPrivilege.PRI_EDIT);
        privilegeArr[1].setPrivilegeName("PRI_EDIT");
        status.setPrivileges(privilegeArr);
        session.setAttribute(sessionStatusKey, status);
        return ResultBody.success("登录成功！", status);
    }

    @GetMapping("/login/check")
    public ResultBody loginCheck(HttpSession session) {
        if (UserUtils.isLoggedIn(session)) {
            return ResultBody.success("已登录！", session.getAttribute(sessionStatusKey));
        }
        return ResultBody.error("未登录");
    }

    @PostMapping("/logout")
    public ResultBody logout(HttpSession session) {
        if (!UserUtils.isLoggedIn(session)) {
            return ResultBody.error(HttpStatus.BAD_REQUEST.value(), "未登录");
        }
        UserStatus status = (UserStatus) session.getAttribute(sessionStatusKey);
        UserUtils.log(session, ActionType.LOGOUT, "用户退出登录：" + status.getUserName());
        session.removeAttribute(sessionStatusKey);
        return ResultBody.success("已退出登录！");
    }

    @PostMapping("/change-pw")
    public ResultBody changePassword(@RequestBody UserReqData vo, HttpSession session) {
        if (!UserUtils.isLoggedIn(session)) {
            return ResultBody.error(HttpStatus.BAD_REQUEST.value(), "未登录");
        }
        // 只允许修改自己的密码
        UserStatus status = (UserStatus) session.getAttribute(sessionStatusKey);
        if (!status.getUserName().equals(vo.getUsername())) {
            return ResultBody.error(HttpStatus.BAD_REQUEST.value(), "只允许用户修改自己的密码 test");
        }
        return ResultBody.success("成功 test");
    }

    @GetMapping("/list")
    public ResultBody list(HttpSession session) {
        UserUtils.checkPrivilege(UserPrivilege.PRI_EDIT, "用户无权限进行用户管理操作");
        List<UserModelForList> list = new ArrayList<>();
        UserModelForList user1 = new UserModelForList();
        user1.setUserId(1);
        user1.setUserName("1");
        user1.setRoleId(1);
        list.add(user1);
        UserModelForList user2 = new UserModelForList();
        user2.setUserId(2);
        user2.setUserName("2");
        user2.setRoleId(2);
        list.add(user2);
        return ResultBody.success(list);
    }

    @PostMapping("/register")
    public ResultBody register(@RequestBody UserReqData vo, HttpSession session) {
        UserUtils.checkPrivilege(UserPrivilege.PRI_EDIT, "用户无权限进行用户管理操作");
        return ResultBody.success("成功");
    }

    @DeleteMapping("/{id}")
    public ResultBody delete(@PathVariable("id") Integer id, HttpSession session) {
        UserUtils.checkPrivilege(UserPrivilege.PRI_EDIT, "用户无权限进行用户管理操作");
        UserStatus status = (UserStatus) session.getAttribute(sessionStatusKey);
        if (status.getUserId().equals(id)) {
            return ResultBody.error(HttpStatus.FORBIDDEN.value(), "不能删除自己的账号 test");
        }
        return ResultBody.success("成功 test");
    }
}
