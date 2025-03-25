package Maven.Book_Store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import Maven.Book_Store.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
}
