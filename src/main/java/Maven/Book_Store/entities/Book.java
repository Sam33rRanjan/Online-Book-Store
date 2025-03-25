package Maven.Book_Store.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // This is the primary key
	@Column(unique = true)
	String title; // This is the title of the book
	String author; // This is the author of the book
	BigDecimal price; // This is the price of the book
	LocalDate publishedDate; // This is the date of publication of the book
	
	public Book() {
		super();
	}
	
	public Book(String title, String author, BigDecimal price, LocalDate publishedDate) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.publishedDate = publishedDate;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public LocalDate getPublishedDate() {
		return publishedDate;
	}
	
	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", publishedDate=" + publishedDate + "]";
	}
	
}
