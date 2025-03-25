package Maven.Book_Store.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import Maven.Book_Store.dao.BookRepository;
import Maven.Book_Store.entities.Book;

@Service
public class BookService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks() {
		logger.info("Service method to get all books called");
		logger.trace("This is a trace message");
		return bookRepository.findAll();
	}
	
	public Book getBookById(int id) {
		logger.info("Service method to get book by ID called");
		try {
			Book existingBook = bookRepository.findById(id).get();
			logger.info("Book retrieved successfully with ID: " + id);
			return existingBook;
		} catch (NoSuchElementException e) {
			logger.error("Error in retrieving book as the book with ID: " + id + " does not exist");
			return null;
		}
	}
	
	public String updateBookById(int id, Book book) {
		logger.warn("Service method to update book called");
		try {
			Book existingBook = bookRepository.findById(id).get();
			existingBook.setTitle(book.getTitle());	
			existingBook.setAuthor(book.getAuthor());
			existingBook.setPrice(book.getPrice());
			existingBook.setPublishedDate(book.getPublishedDate());
			bookRepository.save(existingBook);
			logger.info("Book updated successfully with ID: " + id);
			return "Book updated successfully";
		} catch (NoSuchElementException e) {
			logger.error("Error in updating book as the book with ID: " + id + " does not exist");
			return "Unable to update book";
		} catch (DataIntegrityViolationException e) {
			logger.error("Error in updating book as the book with title: " + book.getTitle() + " already exists");
			return "Unable to update book";
		}
	}
	
	public String deleteBookById(int id) {
		logger.warn("Service method to delete book called");
		try {
			Book existingBook = bookRepository.findById(id).get();
			bookRepository.delete(existingBook);
			logger.info("Book deleted successfully with ID: " + id);
			return "Book deleted successfully";
		} catch (NoSuchElementException e) {
			logger.error("Error in deleting book as the book with ID: " + id + " does not exist");
			return "Unable to delete book";
		}
	}
	
	public String addBook(Book book) {
		logger.info("Service method to add book called");
		try {
			Book savedBook = bookRepository.save(book);
			logger.info("Book added successfully with ID: " + savedBook.getId());		
			return "Book added successfully with ID: " + savedBook.getId();
		} catch (DataIntegrityViolationException e) {
			logger.error("Error in adding book as the book with title: " + book.getTitle() + " already exists");
			return "Unable to add book";
		}
	}
	
}
