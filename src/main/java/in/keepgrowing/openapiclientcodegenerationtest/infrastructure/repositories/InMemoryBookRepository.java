package in.keepgrowing.openapiclientcodegenerationtest.infrastructure.repositories;

import in.keepgrowing.openapiclientcodegenerationtest.domain.Book;
import in.keepgrowing.openapiclientcodegenerationtest.domain.BookRepository;
import in.keepgrowing.openapiclientcodegenerationtest.infrastructure.datainit.DataInitializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InMemoryBookRepository implements BookRepository {

    private final SpringBookRepository springBookRepository;

    public InMemoryBookRepository(DataInitializer dataInitializer,
                                  SpringBookRepository springBookRepository) {
        this.springBookRepository = springBookRepository;
        initDatabase(dataInitializer);
    }

    private void initDatabase(DataInitializer dataInitializer) {
        var books = dataInitializer.initBooks();
        saveAll(books);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return springBookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.springBookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return this.springBookRepository.save(book);
    }

    @Override
    public void saveAll(List<Book> books) {
        this.springBookRepository.saveAll(books);
    }
}
