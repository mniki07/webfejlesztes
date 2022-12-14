package hu.unideb.inf.core.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String title;

    private String author;

    private String genre;

    private Integer numberOfPages;

    @Override
    public String toString() {
        return "Title: "
                + title
                + ", written by: "
                + author
                + ", the genre is: "
                + genre
                + ", and the book is "
                + numberOfPages
                + " pages long.";
    }

}
