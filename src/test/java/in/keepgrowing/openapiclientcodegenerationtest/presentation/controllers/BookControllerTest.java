package in.keepgrowing.openapiclientcodegenerationtest.presentation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.keepgrowing.openapiclientcodegenerationtest.domain.Book;
import in.keepgrowing.openapiclientcodegenerationtest.domain.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(value = BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookController controller;

    @MockBean
    private BookRepository bookRepository;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }

    @Test
    void shouldFindBookById() throws Exception {
        var book = getBook();

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        var expectedResponse = objectMapper.writeValueAsString(book);

        mvc.perform(get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    private Book getBook() {
        return Book.builder()
                .id(1L)
                .title("title")
                .isbn("isbn")
                .publisher("publisher")
                .firstPublicationDate(LocalDate.now())
                .build();
    }

    @Test
    void findAll() throws Exception {
        var pageable = PageRequest.of(1, 2);
        var books = singletonList(getBook());
        var bookPage = new PageImpl<>(books, pageable, 1);

        when(bookRepository.findAll(pageable))
                .thenReturn(bookPage);

        var expectedResponse = objectMapper.writeValueAsString(bookPage);

        mvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("size", "2")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    void save() throws Exception {
        var book = getBook();

        when(bookRepository.save(book))
                .thenReturn(book);

        var expectedResponse = objectMapper.writeValueAsString(book);

        mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedResponse))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}