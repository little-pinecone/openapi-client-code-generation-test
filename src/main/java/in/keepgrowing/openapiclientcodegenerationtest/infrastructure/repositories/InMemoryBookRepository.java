package in.keepgrowing.openapiclientcodegenerationtest.infrastructure.repositories;

import in.keepgrowing.openapiclientcodegenerationtest.domain.Book;
import in.keepgrowing.openapiclientcodegenerationtest.domain.BookRepository;
import in.keepgrowing.openapiclientcodegenerationtest.infrastructure.datainit.DataInitializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InMemoryBookRepository implements BookRepository {

    private final List<Book> books;

    public InMemoryBookRepository(DataInitializer dataInitializer) {
        this.books = dataInitializer.initBooks();
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    @Override
    public Book save(Book book) {
        books.add(book);

        return book;
    }
}
