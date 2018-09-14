package vv.hhpo.bookstore.webcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class BookController {
	
	@GetMapping("/addbook")
	public String addBook() {
		return "Book not added";
	}

}
