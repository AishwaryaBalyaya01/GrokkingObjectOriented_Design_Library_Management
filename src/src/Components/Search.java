package Components;

import java.util.Date;
import java.util.List;

public interface Search {
    public List<Book> searchByTitle(String title);
    public List<Author> searchByAuthor(String author);
}
