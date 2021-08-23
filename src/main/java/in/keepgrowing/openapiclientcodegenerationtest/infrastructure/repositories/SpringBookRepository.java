package in.keepgrowing.openapiclientcodegenerationtest.infrastructure.repositories;

import in.keepgrowing.openapiclientcodegenerationtest.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringBookRepository extends JpaRepository<Book, Long> {
}
