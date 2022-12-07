package com.bit.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author fantastic
 */
@EnableTransactionManagement
@MapperScan(basePackages = "com.bit.book.mapper")
@SpringBootApplication
public class BookDetailApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookDetailApplication.class, args);
    }

}
