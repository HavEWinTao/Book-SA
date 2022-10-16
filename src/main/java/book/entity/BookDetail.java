package book.entity;

import lombok.Data;

@Data
public class BookDetail {
	Integer id;
	String name;
	String author;
	String publisher;
	String time;
	String image;
	String introduction;
}
