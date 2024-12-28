package Components;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Catalogue implements Search{
    private HashMap<String, List<Book>> bookTitles;
    private HashMap<String, List<Author>> bookAuthors;

    public Catalogue(HashMap<String, List<Book>> bookTitles, HashMap<String, List<Author>> bookAuthors) {
        this.bookTitles = bookTitles;
        this.bookAuthors = bookAuthors;
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return bookTitles.get(title);
    }

    @Override
    public List<Author> searchByAuthor(String author) {
        return bookAuthors.get(author);
    }
}
