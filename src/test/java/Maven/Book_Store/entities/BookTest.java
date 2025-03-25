package Maven.Book_Store.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BookTest {

	static Book book;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		book=new Book();
		book.setId(1);
		book.setTitle("The Alchemist");
		book.setAuthor("Paulo Coelho");
		book.setPrice(new BigDecimal(500));
		book.setPublishedDate(LocalDate.of(1988, 8, 1));
	}
	
	@Test
	void testGetId() {
		assertEquals(1, book.getId());
	}
	
	@Test
	void testGetTitle() {
		assertEquals("The Alchemist", book.getTitle());
	}
	
	@Test
	void testGetAuthor() {
		assertEquals("Paulo Coelho", book.getAuthor());
	}
	
	@Test
	void testGetPrice() {
		assertEquals(new BigDecimal(500), book.getPrice());
	}
	
	@Test
	void testGetPublishedDate() {
		assertEquals(LocalDate.of(1988, 8, 1), book.getPublishedDate());
	}

}
