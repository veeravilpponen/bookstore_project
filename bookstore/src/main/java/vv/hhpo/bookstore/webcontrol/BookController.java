package vv.hhpo.bookstore.webcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
	
	@GetMapping("/index")
	public String x() {
		return "";
	}

}
