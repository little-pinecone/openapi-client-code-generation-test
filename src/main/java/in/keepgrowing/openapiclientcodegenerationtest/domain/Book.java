package in.keepgrowing.openapiclientcodegenerationtest.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Book {

    private Long id;
    private String title;
    private String isbn;
    private String publisher;
    private LocalDate firstPublicationDate;
}
