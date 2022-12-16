package com.bit.book.service;

import com.bit.book.entity.Book;
import com.bit.book.vo.BookSearchReqData;
import com.bit.book.vo.PageRspData;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fanhongtao
 * 2022/10/13 15:32
 */
public interface BookService extends IService<Book> {

    PageRspData<Book> listByPage(Integer pageNum, Integer pageSize);

    PageRspData<Book> searchByPage(Integer pageNum, Integer pageSize, BookSearchReqData query);

    boolean add(Book reqData);

    boolean updateBook(Book reqData);

    boolean subOne(Integer bookId);

    int addAll(MultipartFile file);
}
