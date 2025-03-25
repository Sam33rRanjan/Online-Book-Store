package Maven.Book_Store.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import Maven.Book_Store.entities.Book;
import Maven.Book_Store.service.BookService;

@SpringBootTest
class BookControllerTest {

	@InjectMocks
	private BookController bookController;
	
	@Mock
	private BookService bookService;
	
	static private List<Book> books = new ArrayList<Book>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Book book1=new Book("The Alchemist", "Paulo Coelho", new BigDecimal(500), LocalDate.of(1988, 8, 1));
		Book book2=new Book("The Da Vinci Code", "Dan Brown", new BigDecimal(700), LocalDate.of(2003, 3, 18));
		books.add(book1);
		books.add(book2);
		System.out.println(books);
	}
	
	@Test
	void testGetBooks() {
		when(bookService.getAllBooks()).thenReturn(books);
		assertEquals(2, bookController.getBooks().size());
		assertEquals("The Alchemist", bookController.getBooks().get(0).getTitle());
		assertEquals("Paulo Coelho", bookController.getBooks().get(0).getAuthor());
		assertEquals(new BigDecimal(500), bookController.getBooks().get(0).getPrice());
		assertEquals(LocalDate.of(1988, 8, 1), bookController.getBooks().get(0).getPublishedDate());
		assertEquals("The Da Vinci Code", bookController.getBooks().get(1).getTitle());
		assertEquals("Dan Brown", bookController.getBooks().get(1).getAuthor());
		assertEquals(new BigDecimal(700), bookController.getBooks().get(1).getPrice());
		assertEquals(LocalDate.of(2003, 3, 18), bookController.getBooks().get(1).getPublishedDate());
	}
	
	@Test
	void testGetBook() {
		when(bookService.getBookById(1)).thenReturn(books.get(0));
		assertEquals("The Alchemist", bookController.getBook(1).getTitle());
		assertEquals("Paulo Coelho", bookController.getBook(1).getAuthor());
		assertEquals(new BigDecimal(500), bookController.getBook(1).getPrice());
		assertEquals(LocalDate.of(1988, 8, 1), bookController.getBook(1).getPublishedDate());
		assertNull(bookController.getBook(3));
	}
	
	@Test
	void testUpdateBook() {
		when(bookService.updateBookById(1, books.get(0))).thenReturn("Book updated successfully");
		Book book=new Book("The Alchemist", "Paulo Coelho", new BigDecimal(500), LocalDate.of(1988, 8, 1));
		assertNull(bookController.updateBook(1, book));
		assertNull(bookController.updateBook(3, books.get(0)));
		assertEquals("Book updated successfully", bookController.updateBook(1, books.get(0)));
	}
	
	@Test
	void testDeleteBook() {
		when(bookService.deleteBookById(1)).thenReturn("Book deleted successfully");
		assertEquals("Book deleted successfully", bookController.deleteBook(1));
		assertNull(bookController.deleteBook(3));
	}
	
	@Test
	void testAddBook() {
		when(bookService.addBook(books.get(0))).thenReturn("Book added successfully with ID: " + books.get(0).getId());
		assertEquals("Book added successfully with ID: 0", bookController.addBook(books.get(0)));
		assertNull(bookController.addBook(books.get(1)));
	}

}
