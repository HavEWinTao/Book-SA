package com.bit.book.service;

import com.bit.book.entity.Book;
import com.bit.book.entity.BorrowInfo;
import com.bit.book.entity.UserLog;
import com.bit.book.vo.BorrowInfoSearchData;
import com.bit.book.vo.PageRspData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author fanhongtao
 * 2022/10/13 15:32
 */
public interface BorrowInfoService extends IService<BorrowInfo> {

    PageRspData<BorrowInfo> listByPage(Integer pageNum, Integer pageSize);

    PageRspData<BorrowInfo> searchByPage(Integer pageNum, Integer pageSize, BorrowInfoSearchData query);

    boolean borrowBook(Integer bookId, Integer userId);

    boolean deleteOne(Integer userId, Integer bookId);
}
