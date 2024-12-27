package Components;

import java.util.Date;
import java.util.List;

public class Book {
    private String ISBN;
    private String title;
    private String subject;
    private List<Author> authors;
    private Date publishedDate;
    private String language;

    public Book(String ISBN, String title, String subject, List<Author> authors, Date publishedDate, String language) {
        this.ISBN = ISBN;
        this.title = title;
        this.subject = subject;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.language = language;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
