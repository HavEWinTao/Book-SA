package book.controller;


import book.entity.BookDetail;
import book.utils.ResultBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book/books")
public class BookController {

	@GetMapping("/detail/{id}")
	public ResultBody getById(@PathVariable Integer id){
		BookDetail book = new BookDetail();
		book.setId(1);
		book.setName("三体test");
		book.setAuthor("刘慈溪");
		book.setPublisher("人民邮电出版社");
		book.setTime("2017");
		book.setImage(null);
		book.setIntroduction("这是一本科幻小说");
		return ResultBody.success(book);
	}
}
