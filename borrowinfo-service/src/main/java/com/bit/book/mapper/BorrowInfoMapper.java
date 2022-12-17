package com.bit.book.mapper;

import com.bit.book.entity.BorrowInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author mengwenheng
 * 2022/12/13 18:22
 */
public interface BorrowInfoMapper extends BaseMapper<BorrowInfo> {
    Boolean canBorrow(@Param("bookId") Integer bookId);

    boolean subOne(Integer bookId);
}
