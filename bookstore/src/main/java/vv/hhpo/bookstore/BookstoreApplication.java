package vv.hhpo.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vv.hhpo.bookstore.model.Book;
import vv.hhpo.bookstore.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository) {
		return (args) -> {
			log.info("save these books");
			//ajetaan tietokantaan
			bookRepository.save(new Book("Tie Myrskyluodolle", "Anni Blomqvist", 1974, "978-95-1208-3", 15.90)); 
			bookRepository.save(new Book("Sarasvatin hiekkaa", "Risto Isomäki", 2005, "951-31-3422-9", 26.90));	
			
			log.info("fetch books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
	
	
}
