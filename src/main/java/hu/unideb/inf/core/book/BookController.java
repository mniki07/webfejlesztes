package hu.unideb.inf.core.book;

import hu.unideb.inf.core.book.model.BookDto;
import hu.unideb.inf.core.book.persistence.entity.Book;
import hu.unideb.inf.core.user.UserAlreadyExistsException;
import hu.unideb.inf.core.user.UserService;
import hu.unideb.inf.core.user.persistence.entity.MyUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    private final UserService userService;

    @GetMapping("/")
    public String showDefaultStartingPage(Model model) {
        model.addAttribute("bookList", bookService.retrieveAllBooks());
        return "index";
    }

    // Read
    @GetMapping("/index")
    public String showBookList(Model model) {
        model.addAttribute("bookList", bookService.retrieveAllBooks());
        return "index";
    }

    @GetMapping("/showNewBookForm")
    public String showNewBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "newBook";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.createBook(new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getNumberOfPages()
        ));
        return "redirect:/index";
    }

    @GetMapping("/showUpdateFormForBook/{bookId}")
    public String showUpdateFormForBook(@PathVariable("bookId") Integer id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        model.addAttribute("book", book.get());
        return "updateBook";
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute("book") Book book) {
        bookService.updateBook(book);
        return "redirect:/index";
    }

    @GetMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") Integer id) {
        bookService.deleteBookById(id);
        return "redirect:/index";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new MyUser("", "", MyUser.Role.USER));
        return "signup";
    }

    @PostMapping("/makeRegistration")
    public String registerUser(@ModelAttribute("user") MyUser user, Model model) {
        try {
            userService.registerUser(user.getUsername(), user.getPassword());
            return "redirect:/index";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("user", new MyUser("", "", MyUser.Role.USER));
            return "signup";
        }
    }

}
