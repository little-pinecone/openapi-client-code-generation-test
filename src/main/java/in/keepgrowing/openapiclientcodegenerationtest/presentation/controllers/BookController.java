package in.keepgrowing.openapiclientcodegenerationtest.presentation.controllers;

import in.keepgrowing.openapiclientcodegenerationtest.domain.Book;
import in.keepgrowing.openapiclientcodegenerationtest.domain.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "books", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("{bookId}")
    @Operation(summary = "Find a book by id")
    public ResponseEntity<Book> findById(@PathVariable Long bookId) {
        return bookRepository.findById(bookId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    @Operation(summary = "Get paginated books")
    public ResponseEntity<Page<Book>> findAll(Pageable pageable) {
        return ResponseEntity.ok(bookRepository.findAll(pageable));
    }

    @PostMapping
    @Operation(summary = "Save a new book")
    public ResponseEntity<Book> save(@RequestBody Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @PostMapping(path = "with-cover", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Save a new book (and accept additional file to test multipart requests)")
    public ResponseEntity<Book> saveWithCover(
            @RequestPart Book book,
            @RequestPart(value = "cover") MultipartFile file
    ) {
        return ResponseEntity.ok(bookRepository.save(book));
    }
}
