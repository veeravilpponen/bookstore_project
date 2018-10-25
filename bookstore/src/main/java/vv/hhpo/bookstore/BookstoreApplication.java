package vv.hhpo.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vv.hhpo.bookstore.model.Book;
import vv.hhpo.bookstore.model.BookRepository;
import vv.hhpo.bookstore.model.Category;
import vv.hhpo.bookstore.model.CategoryRepository;
import vv.hhpo.bookstore.model.User;
import vv.hhpo.bookstore.model.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("save these books & categories");
			
			//ajetaan tietokantaan kategorioita
			categoryRepository.save(new Category("Tietokirja"));
			categoryRepository.save(new Category("Sarjakuva"));
			categoryRepository.save(new Category("Kaunokirja"));
			
			//ajetaan tietokantaan kirjoja
			bookRepository.save(new Book("Tie Myrskyluodolle", "Anni Blomqvist", 1974, "978-95-1208-3", 15.90, categoryRepository.findByName("Kaunokirja").get(0))); 
			bookRepository.save(new Book("Sarasvatin hiekkaa", "Risto Isomäki", 2005, "951-31-3422-9", 26.90, categoryRepository.findByName("Kaunokirja").get(0)));
			bookRepository.save(new Book("Aku Ankan Taskukirja - Maalitykki", "Useita", 2016, "891-21-3982-134", 9.90, categoryRepository.findByName("Sarjakuva").get(0)));
			bookRepository.save(new Book("Verkkorikokset", "Ari Haasio", 2017, "978-95-1692-6677", 41.90, categoryRepository.findByName("Tietokirja").get(0)));
			
			//ajetaan tietokantaan käyttäjiä
			userRepository.save(new User("user1", "$2a$10$gOZBXoElzBYwEu6E.FrYlulMI2SdsbPqEktLvrGAEyUVlgKIE4lfG", "USER", "user1@1.com"));
			userRepository.save(new User("user2", "$2a$10$dipRrajxRQgeNShTmFrqH.YMjQrEbszjTzFiTjpHoeIKypijcYz9e", "USER", "user2@2.com"));
			userRepository.save(new User("admin", "$2a$10$QJbRqEQTo0uHF4U3.ac8a.PjUCNCb4pEsiGEIpEgvKyyV9.o6wrzS", "ADMIN", "admin@admin.com"));
			
			log.info("fetch books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
	
	
}
