package in.keepgrowing.openapiclientcodegenerationtest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Page<Book> findAll(Pageable pageable);

    Optional<Book> findById(Long id);

    Book save(Book book);

    void saveAll(List<Book> books);
}
