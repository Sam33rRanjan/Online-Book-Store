package Maven.Book_Store.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import Maven.Book_Store.dao.BookRepository;
import Maven.Book_Store.entities.Book;

@SpringBootTest
class BookServiceTest {
	
	@Mock
	BookRepository bookRepository;
	
	@InjectMocks
	BookService bookService;
	
	static private List<Book> books = new ArrayList<Book>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// setting up dummy list of books
		Book book1=new Book("The Alchemist", "Paulo Coelho", new BigDecimal(500), LocalDate.of(1988, 8, 1));
		Book book2=new Book("The Da Vinci Code", "Dan Brown", new BigDecimal(700), LocalDate.of(2003, 3, 18));
		books.add(book1);
		books.add(book2);
	}
	
	@Test
	void testGetAllBooks() {
		when(bookRepository.findAll()).thenReturn(books); // mocking the findAll() method of bookRepository
		assertEquals(2, bookService.getAllBooks().size());
		assertEquals("The Alchemist", bookService.getAllBooks().get(0).getTitle());
		assertEquals("Paulo Coelho", bookService.getAllBooks().get(0).getAuthor());
		assertEquals(new BigDecimal(500), bookService.getAllBooks().get(0).getPrice());
		assertEquals(LocalDate.of(1988, 8, 1), bookService.getAllBooks().get(0).getPublishedDate());
		assertEquals("The Da Vinci Code", bookService.getAllBooks().get(1).getTitle());
		assertEquals("Dan Brown", bookService.getAllBooks().get(1).getAuthor());
		assertEquals(new BigDecimal(700), bookService.getAllBooks().get(1).getPrice());
		assertEquals(LocalDate.of(2003, 3, 18), bookService.getAllBooks().get(1).getPublishedDate());
	}
	
	@Test
	void testGetBookById() {
		when(bookRepository.findById(1)).thenReturn(Optional.of(books.get(0))); // mocking the findById() method of bookRepository
		assertEquals("The Alchemist", bookService.getBookById(1).getTitle());
		assertEquals("Paulo Coelho", bookService.getBookById(1).getAuthor());
		assertEquals(new BigDecimal(500), bookService.getBookById(1).getPrice());
		assertEquals(LocalDate.of(1988, 8, 1), bookService.getBookById(1).getPublishedDate());
		assertNull(bookService.getBookById(2));
	}
	
	@Test
	void testUpdateBookById() {
		when(bookRepository.findById(1)).thenReturn(Optional.of(books.get(0))); // mocking the findById() method of bookRepository
		Book book=new Book("The Alchemist", "Paulo Coelho", new BigDecimal(600), LocalDate.of(1988, 8, 1));
		assertEquals("Book updated successfully", bookService.updateBookById(1, book));
		assertEquals(new BigDecimal(600), bookService.getBookById(1).getPrice());
		assertEquals("Unable to update book", bookService.updateBookById(2, book));
	}
	
	@Test
	void testDeleteBookById() {
		when(bookRepository.findById(1)).thenReturn(Optional.of(books.get(0))); // mocking the findById() method of bookRepository
		assertEquals("Book deleted successfully", bookService.deleteBookById(1));
		assertEquals("Unable to delete book", bookService.deleteBookById(2));
	}
	
	@Test
	void testAddBook() {
		when(bookRepository.save(books.get(0))).thenReturn(books.get(0)); // mocking the save() method of bookRepository
		assertEquals("Book added successfully with ID: 0", bookService.addBook(books.get(0)));
		assertThrows(NullPointerException.class, () -> bookService.addBook(books.get(1)));
	}

}
