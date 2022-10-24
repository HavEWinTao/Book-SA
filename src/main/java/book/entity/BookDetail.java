package book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author fanhongtao
 * 2022/10/12 15:29
 */
@Data
@TableName("book_detail")
public class BookDetail {
    private static final long serialVersionUID = 1L;
    @TableId(value = "book_id")
    private Integer bookId;

    @TableField(exist = false)
    @JsonProperty("ISBN")
    private String ISBN;

    //书名
    private String bookName;

    //作者
    private String bookAuthor;

    private String publisher;

    //yyyy，如2017
    private String time;

    //封面字符串
    private String image;

    //书籍简介
    private String introduction;
}
