package Maven.Book_Store.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Maven.Book_Store.entities.Book;
import Maven.Book_Store.service.BookService;

@RestController // This annotation is used to create a RESTController class
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired // This annotation is used to inject the object automatically
	private BookService bookService;
	
	@GetMapping("/books") // This annotation is used to create a GET request
	public List<Book> getBooks() {
		logger.info("Controller method to get all books called");
		return bookService.getAllBooks();
	}
	
	@PutMapping("/books/{id}") // This annotation is used to create a PUT request
	public String updateBook(@PathVariable("id") int id,@RequestBody Book book) {
		logger.warn("Controller method to update book called");
		return bookService.updateBookById(id, book);
	}
	
	@DeleteMapping("/books/{id}") // This annotation is used to create a DELETE request
	public String deleteBook(@PathVariable("id") int id) {
		logger.warn("Controller method to delete book called");
		return bookService.deleteBookById(id);
	}
	
	@PostMapping("/books") // This annotation is used to create a POST request
	public String addBook(@RequestBody Book book) {
		logger.info("Controller method to add book called");
		return bookService.addBook(book);
	}
	
}
