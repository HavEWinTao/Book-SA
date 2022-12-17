package com.bit.book.vo;

import lombok.Data;

/**
 * @author fanhongtao
 * @date 2022/10/15 19:31
 */
@Data
public class BorrowInfoData {
    Integer bookId;

    String bookName;

    Integer userId;

    String userName;
}
