package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String book_title;
	private String book_author;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String book_title, String book_author) {
		super();
		this.book_title = book_title;
		this.book_author = book_author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	@Override
	public String toString() {
		return "Book id=" + id + ", Book Title=" + book_title + ", Book Author=" + book_author;
	}
	
}
