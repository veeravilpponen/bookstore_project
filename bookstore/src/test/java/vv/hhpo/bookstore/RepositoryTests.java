package vv.hhpo.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import vv.hhpo.bookstore.model.Book;
import vv.hhpo.bookstore.model.BookRepository;
import vv.hhpo.bookstore.model.Category;
import vv.hhpo.bookstore.model.CategoryRepository;
import vv.hhpo.bookstore.model.User;
import vv.hhpo.bookstore.model.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {

	EntityManager entityManager;
	
	@Autowired
    private BookRepository bookRepository;
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	//BookRepository
	
	//testaa pystytäänkö luomaan kirja
	@Test
    public void createNewBook() {
    	Book book = new Book("Hopsanssaa", "Hermanni Hupsu", 1954, "980-95-0808-1", 6.90, categoryRepository.findByName("Kaunokirja").get(0));
    	bookRepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
	
	/*
	 * Ei toimi jostain syystä, kun yrittää luoda uuden Categoryn..
	@Test
    public void createNewBook2() {
    	Book book = new Book("Hopsanssaa", "Hermanni Hupsu", 1954, "980-95-0808-1", 6.90, new Category("Lastenkirja"));
    	bookRepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
    */
    
	
	//testaa pystytäänkö etsimään kirja
	@Test
	public void findByTitleShouldReturnBook() {
	    List<Book> books = bookRepository.findByTitle("Tie Myrskyluodolle");
	        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Anni Blomqvist");
    }
	
	//testaa pystytäänkö poistamaan kirja
	
	/*
	@Test
	public void deleteBook() {
		Book book = new Book("Maailman vahvin nalle", "Useita", 1992, "937-61-1048-3", 11.90, categoryRepository.findByName("Sarjakuva").get(0));
		bookRepository.save(book);
		Long id = book.getId();
		bookRepository.deleteById(id);
    	//assertThat(bookRepository.findAll()).hasSize(3);
		assertThat(bookRepository.findById(id)).isNull();
	}
	*/
	
	@Test
	public void deleteBook() {
		Book book = new Book("Maailman vahvin nalle", "Useita", 1992, "937-61-1048-3", 11.90, categoryRepository.findByName("Sarjakuva").get(0));
		bookRepository.save(book);
		Long id = book.getId();
		bookRepository.deleteById(id);
		Optional<Book> optbook = bookRepository.findById(id);
		assertThat(optbook).isEmpty();
	}
	
	// CategoryRepository
	
	//testaa pystytäänkö luomaan category
	@Test
    public void createNewCategory() {
    	Category category = new Category("Kaunokirja");
    	categoryRepository.save(category);
    	assertThat(category.getCategoryId()).isNotNull();
    }
	
	//testaa pystytäänkö etsimään category
	@Test
	public void findByNameShouldReturnCategory() {
	    List<Category> categories = categoryRepository.findByName("Kaunokirja");
	        
        assertThat(categories).hasSize(1);
    }
	
	//testaa pystytäänkö poistamaan kategoria
	@Test
	public void deleteCategory() {
		Category category = new Category("Lastenkirja");
		categoryRepository.save(category);
		Long id = category.getCategoryId();
		categoryRepository.deleteById(id);
		Optional<Category> optcategory = categoryRepository.findById(id);
		assertThat(optcategory).isEmpty();
	}
	

	//UserRepository
	
	//testaa pystytäänkö luomaan käyttäjä
	@Test
    public void createNewUser() {
    	User user = new User("user3", "$2a$10$gOZBXoElzBYwEu6E.FrYlulMI2SdsbPqEktLvrGAEyUVlgKIE4lfG", "USER", "user3@3.com");
    	userRepository.save(user);
    	assertThat(user.getId()).isNotNull();
    }

	//testaa pystytäänkö etsimään käyttäjää
	@Test
	public void findByUsernameShouldReturnUser() {
	    User user = userRepository.findByUsername("user1");
	    
        assertThat(user.getUsername()).isEqualTo("user1");
    }

	//testaa pystytäänkö poistamaan kategoria
		@Test
		public void deleteUser() {
			User user = new User("user1", "$2a$10$gOZBXoElzBYwEu6E.FrYlulMI2SdsbPqEktLvrGAEyUVlgKIE4lfG", "USER", "user1@1.com");
			userRepository.save(user);
			Long id = user.getId();
			userRepository.deleteById(id);
			Optional<User> optuser = userRepository.findById(id);
			assertThat(optuser).isEmpty();
		}
}
