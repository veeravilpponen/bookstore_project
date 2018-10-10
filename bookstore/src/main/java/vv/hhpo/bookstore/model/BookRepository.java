package vv.hhpo.bookstore.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource // rajapinnan metodit näkyvät nyt myös REST-palveluna, controllerin lisäksi
public interface BookRepository extends CrudRepository<Book, Long> {

	// http://localhost:8080/api/books/search/findByTitle?title=Verkkorikokset
    List<Book> findByTitle(@Param(value= "title") String title);
    
}
