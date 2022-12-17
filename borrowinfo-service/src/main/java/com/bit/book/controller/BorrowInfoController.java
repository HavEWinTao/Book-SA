package com.bit.book.controller;

import com.bit.book.entity.BorrowInfo;
import com.bit.book.entity.Privilege;
import com.bit.book.entity.UserStatus;
import com.bit.book.exception.BasicException;
import com.bit.book.service.BorrowInfoService;
import com.bit.book.utils.ResultBody;
import com.bit.book.utils.UserUtils;
import com.bit.book.vo.BorrowInfoData;
import com.bit.book.vo.BorrowInfoSearchData;
import com.bit.book.vo.PageRspData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fanhongtao
 * 2022/10/13 21:42
 */
@RestController
@RequestMapping("/borrow")
public class BorrowInfoController {

    @Autowired
    private BorrowInfoService borrowInfoService;

    /**
     * 根据条件分页查询
     */
    @PostMapping("/search")
    public ResultBody searchByPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestBody BorrowInfoSearchData query) {
        // UserUtils.checkPrivilege(Privilege.PRI_READ, "用户无权限查看数据");
        PageRspData<BorrowInfo> pageRspData;
        if ("".equals(query.getBookName()) && "".equals(query.getUserName())) {
            pageRspData = borrowInfoService.listByPage(pageNum, pageSize);
        } else {
            pageRspData = borrowInfoService.searchByPage(pageNum, pageSize, query);
        }
        return ResultBody.success("查询成功", pageRspData);
    }

    //删除一个借书信息
    @DeleteMapping
    public ResultBody deleteDetail(@RequestBody BorrowInfoData borrowInfo) {
        // UserUtils.checkPrivilege(Privilege.PRI_EDIT, "用户无权限修改数据");
        Integer bookId = borrowInfo.getBookId();
        Integer userId = borrowInfo.getUserId();
        boolean flag = borrowInfoService.deleteOne(userId, bookId);
        if (!flag) {
            throw new BasicException(400, "还书失败");
        }
        return ResultBody.success("还书成功");
    }

    //借一本书
    @PostMapping
    public ResultBody borrowBook(@RequestBody BorrowInfoData borrowInfo) {
        // UserUtils.checkPrivilege(Privilege.PRI_READ, "用户借书权限");
        boolean flag = borrowInfoService.borrowBook(borrowInfo);
        if (!flag) {
            throw new BasicException(400, "借书失败");
        }
        return ResultBody.success("借书成功");
    }
}
