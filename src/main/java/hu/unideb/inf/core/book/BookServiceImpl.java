package hu.unideb.inf.core.book;

import hu.unideb.inf.core.book.BookService;
import hu.unideb.inf.core.book.model.BookDto;
import hu.unideb.inf.core.book.persistence.entity.Book;
import hu.unideb.inf.core.book.persistence.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public void createBook(BookDto bookDto) {
        Book book = new Book(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getGenre(),
                bookDto.getNumberOfPages()
        );
        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
        }
    }

    @Override
    public Optional<Book> updateBook(Book newBook) {
        Optional<Book> book = bookRepository.findById(newBook.getId());
        if (book.isPresent()) {
            Book bookValueToUpdate = book.get();
            bookValueToUpdate.setTitle(newBook.getTitle());
            bookValueToUpdate.setAuthor(newBook.getAuthor());
            bookValueToUpdate.setGenre(newBook.getGenre());
            bookValueToUpdate.setNumberOfPages(newBook.getNumberOfPages());
            bookRepository.save(bookValueToUpdate);
            return Optional.of(bookValueToUpdate);
        }
        return Optional.empty();
    }

    @Override
    public List<Book> retrieveAllBooks() {
        return bookRepository.findAll()/*.stream()
                .map(this::convertFromEntityToDto)
                .collect(Collectors.toList())*/;
    }

    @Override
    public Optional<BookDto> getBookByTitle(String title) {
        Optional<Book> book = bookRepository.findByTitle(title);
        if (book.isPresent()) {
            return Optional.of(convertFromEntityToDto(book.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    @Override
    public Optional<Book> getBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book;
        }
        return Optional.empty();
    }

    private BookDto convertFromEntityToDto(Book book) {
        return new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getNumberOfPages()
        );
    }
}
