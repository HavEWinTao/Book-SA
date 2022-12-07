package com.bit.book.mapper;

import com.bit.book.entity.BorrowInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author fanhongtao
 * 2022/10/13 15:32
 */
public interface BorrowInfoMapper extends BaseMapper<BorrowInfo> {
    Boolean canBorrow(@Param("bookId") Integer bookId);
}
