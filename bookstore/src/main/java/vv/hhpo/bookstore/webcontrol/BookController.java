package vv.hhpo.bookstore.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vv.hhpo.bookstore.model.BookRepository;

@Controller
public class BookController {
	
	//lisätään luokkaan repository attribuuttina
	//attribuutilta voi pyytää tietokantakäsittelyjä, esim delete, save
	@Autowired
	private BookRepository bookRepository;
	
	//haetaan kirjojen tiedot tietokannasta
	@GetMapping("/allbooks")
	public String listBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist"; //html
	}

}
