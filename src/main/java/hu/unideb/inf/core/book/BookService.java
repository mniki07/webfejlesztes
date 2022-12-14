package hu.unideb.inf.core.book;

import hu.unideb.inf.core.book.model.BookDto;
import hu.unideb.inf.core.book.persistence.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void createBook(BookDto bookDto);

    void deleteBookById(Integer id);

    Optional<Book> updateBook(Book newBook);

    List<Book> retrieveAllBooks();

    Optional<BookDto> getBookByTitle(String title);

    void deleteAllBooks();

    Optional<Book> getBookById(Integer id);

}
