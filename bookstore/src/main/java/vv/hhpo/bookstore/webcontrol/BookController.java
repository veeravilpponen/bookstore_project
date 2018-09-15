package vv.hhpo.bookstore.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vv.hhpo.bookstore.model.Book;
import vv.hhpo.bookstore.model.BookRepository;

@Controller
public class BookController {
	
	//lisätään luokkaan repository attribuuttina
	//attribuutilta voi pyytää tietokantakäsittelyjä, esim delete, save
	@Autowired
	private BookRepository bookRepository;
	
	//haetaan kirjojen tiedot tietokannasta
	@GetMapping("/booklist")
	public String listBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist"; //html
	}
	
	// metodi poistaa tietokannasta valitun kirjan id:n perusteella
	@GetMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		//mene uuteen osoitteeseen
		return "redirect:../booklist";
	}
	
	// palauttaa tyhjän lomakkeen, jolla voidaan lisätä uusi kirja
	@RequestMapping(value="/addbook")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        return "addbookform";
    }
	
	// tallettaa uuden kirjan
    @PostMapping("/savebook")
    public String saveBook(Book book){
        bookRepository.save(book);
        return "redirect:booklist";
    }
    
    // päivitetään kirjan tiedot
  	@RequestMapping(value="/editbook/{id}")
  	public String editBook(@PathVariable("id") Long bookId, Model model) {
  		//haetaan valitun kirjan tiedot id:n perusteella
  		model.addAttribute("bookupdated", bookRepository.findById(bookId));
  		return "updatebook";
  	}
  	/*
  	// tallettaa päivitetyn kirjan
    @PostMapping("/saveupdatedbook")
    public String saveUpdatedBook(Book bookupdated){
        bookRepository.save(bookupdated);
        return "redirect:booklist";
    }
  	*/ 	
}
