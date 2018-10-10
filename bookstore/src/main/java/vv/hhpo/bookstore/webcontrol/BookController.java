package vv.hhpo.bookstore.webcontrol;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vv.hhpo.bookstore.model.Book;
import vv.hhpo.bookstore.model.BookRepository;
import vv.hhpo.bookstore.model.CategoryRepository;

@Controller
public class BookController {
	
	// lisätään luokkaan bookrepository attribuuttina
	// attribuutilta voi pyytää tietokantakäsittelyjä, esim delete, save
	@Autowired
	private BookRepository bookRepository;
	
	// toinen repository
	@Autowired
	private CategoryRepository categoryRepository;;
	
	// näytetään kirjojen tiedot tietokannasta
	@GetMapping("/booklist")
	public String listBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist"; //palauttaa stringinä studentlist.html -templaten nimen,
        //niin servlet tietää, että kutsuu thymeleafin muodostamaan html:ää
	}
	
	// REST-palvelu, hae kaikki kirjat
    @RequestMapping(value="/books", method = RequestMethod.GET)
    // @ResponseBody kertoo, että pitää palauttaa json-dataa
    public @ResponseBody List<Book> bookListRest() {		
    	// palauttaa repositorysta haetut kirjat, muuntuu automaattisesti json:ksi
        return (List<Book>) bookRepository.findAll();
    }    

	// REST-palvelu, hae kirja tietyllä id:llä
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
    	return bookRepository.findById(bookId);
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
    	// modelille voi välittää useamman tiedon
    	// mahdolliset kategoriat pitää hakea tietokannasta
    	model.addAttribute("categories", categoryRepository.findAll());
        return "addbookform";
    }
	
	// tallettaa kirjan
    @PostMapping("/savebook")
    public String saveBook(Book book){
        bookRepository.save(book);
        return "redirect:booklist";
    }
    
    // päivitetään kirjan tiedot
  	@RequestMapping(value="/updatebook/{id}")
  	public String editBook(@PathVariable("id") Long bookId, Model model) {
  		//haetaan valitun kirjan tiedot id:n perusteella
  		model.addAttribute("bookupdated", bookRepository.findById(bookId));
  		model.addAttribute("categories", categoryRepository.findAll());
  		return "updatebook";
  	}
  	
  	// kirjautuminen sovellukseen
  	@RequestMapping(value="/login")
	public String login() {
		return "login";
	} 
  	
}
