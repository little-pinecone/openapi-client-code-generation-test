package in.keepgrowing.openapiclientcodegenerationtest.infrastructure.datainit;

import dev.codesoapbox.dummy4j.Dummy4j;
import in.keepgrowing.openapiclientcodegenerationtest.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataInitializer {

    private final Dummy4j dummy4j;

    public DataInitializer() {
        this.dummy4j = new Dummy4j();
    }

    public List<Book> initBooks() {
        return dummy4j.listOf(10, this::generateBook);
    }

    private Book generateBook() {
        return Book.builder()
                .id(dummy4j.number().nextLong(99L, 1000L))
                .title(dummy4j.book().title())
                .isbn(dummy4j.identifier().isbn().toString())
                .publisher(dummy4j.book().publisher())
                .firstPublicationDate(dummy4j.dateAndTime().birthday(1, 10))
                .build();
    }
}
