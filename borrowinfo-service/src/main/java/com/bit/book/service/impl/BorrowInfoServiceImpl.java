package com.bit.book.service.impl;

import com.bit.book.entity.BorrowInfo;
import com.bit.book.exception.BasicException;
import com.bit.book.mapper.BorrowInfoMapper;
import com.bit.book.service.BorrowInfoService;
import com.bit.book.vo.BorrowInfoData;
import com.bit.book.vo.BorrowInfoSearchData;
import com.bit.book.vo.PageRspData;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanhongtao
 * 2022/10/13 15:32
 */
@Service
public class BorrowInfoServiceImpl extends ServiceImpl<BorrowInfoMapper, BorrowInfo> implements BorrowInfoService {

    @Override
    public PageRspData<BorrowInfo> listByPage(Integer pageNum, Integer pageSize) {
        return pageQuery(pageNum, pageSize, null);
    }

    @Override
    public PageRspData<BorrowInfo> searchByPage(Integer pageNum, Integer pageSize, BorrowInfoSearchData query) {
        QueryWrapper<BorrowInfo> wrapper = new QueryWrapper<>();
        String userName = query.getUserName();
        String bookName = query.getBookName();
        wrapper.like("user_name", userName);
        wrapper.like("book_name", bookName);
        return pageQuery(pageNum, pageSize, wrapper);
    }

    private PageRspData<BorrowInfo> pageQuery(Integer pageNum, Integer pageSize, Wrapper<BorrowInfo> wrapper) {
        IPage<BorrowInfo> page = new Page<>(pageNum, pageSize);
        // 分页
        page = this.baseMapper.selectPage(page, wrapper);
        // 构造结果
        List<BorrowInfo> records = page.getRecords();
        return PageRspData.of(records, page.getTotal(), page.getPages());
    }

    @Override
    public boolean borrowBook(BorrowInfoData borrowInfoData) {
        Boolean canBorrow = baseMapper.canBorrow(borrowInfoData.getBookId());
        if (!canBorrow) {
            throw new BasicException(400, "图书数量不足");
        }
        LambdaQueryWrapper<BorrowInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BorrowInfo::getBookId, borrowInfoData.getBookId());
        wrapper.eq(BorrowInfo::getUserId, borrowInfoData.getUserId());
        BorrowInfo borrowInfo = getOne(wrapper);
        if (borrowInfo != null) {
            throw new BasicException(400, "该用户已借该图书");
        }
        borrowInfo = new BorrowInfo();
        borrowInfo.setUserId(borrowInfoData.getUserId());
        borrowInfo.setUserName(borrowInfoData.getUserName());
        borrowInfo.setBookId(borrowInfoData.getBookId());
        borrowInfo.setBookName(borrowInfoData.getBookName());
        //设置时间
        long now = System.currentTimeMillis();
        borrowInfo.setBorrowTime(now);
        long deadline = now + 1000L * 3600 * 24 * 30;
        borrowInfo.setDeadline(deadline);
        boolean flag = this.save(borrowInfo);
        flag = flag && baseMapper.subOne(borrowInfoData.getBookId());
        return flag;
    }

    @Override
    public boolean deleteOne(Integer userId, Integer bookId) {
        LambdaQueryWrapper<BorrowInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(BorrowInfo::getUserId, userId);
        wrapper.eq(BorrowInfo::getBookId, bookId);
        return remove(wrapper);
    }
}
