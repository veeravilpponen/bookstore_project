package vv.hhpo.bookstore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryId;
	private String name;
	
	// Category(1) - book(*)
	// cascade, jos poistat kategorian, niin kaikki sen kategorian kirjat katoaa kannasta, aika radikaalia..?
	// mappedby, viittaa toisen, Book-taulun, attributtin nimeen "private Category category;"
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	// lista kategorian kirjoista
	private List<Book> books;
	
	
	//konstruktorit
	public Category() {
		super();
		this.name = null;
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	//getterit ja setterit
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + "]";
	}
	
}
