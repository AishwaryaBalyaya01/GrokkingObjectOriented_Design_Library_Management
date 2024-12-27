package Components;

import Actors.Member;
import Models.BookFormat;
import Models.BookStatus;
import Models.Constants;
import Models.Rack;

import java.util.Date;
import java.util.List;

public class BookItem extends Book{
    private boolean isReferenceOnly;
    private String barcode;
    private Date issuedDate;
    private Date dueDate;
    private Date dateOfPurchase;
    private Date publicationDate;
    private double price;
    private BookFormat format;
    private BookStatus status;
    private Rack rack;

    public BookItem(String ISBN, String title, String subject, List<Author> authors, Date publishedDate, String language,boolean referenceOnly,
                    String barcode, Date issuedDate, Date dueDate, double price,
                    BookFormat format, BookStatus status, Rack rack) {
        super(ISBN, title, subject, authors, publishedDate, language);
        this.isReferenceOnly=referenceOnly;
        this.barcode = barcode;
        this.format = format;
        this.status = status;
        this.rack = rack;
        this.issuedDate = new java.sql.Date(System.currentTimeMillis());
        this.dueDate = new java.sql.Date(System.currentTimeMillis()+ Constants.MAX_LENDING_DAYS);
        this.price = price;
    }

    public boolean isReferenceOnly() {
        return isReferenceOnly;
    }

    public void setReferenceOnly(boolean referenceOnly) {
        isReferenceOnly = referenceOnly;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookFormat getFormat() {
        return format;
    }

    public void setFormat(BookFormat format) {
        this.format = format;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }


    public void updateBookItemState(BookStatus bookStatus) {
        this.status= bookStatus;
    }

    public void updateDueDate(Date date) {
        System.out.println("Return after: "+date.getDate()+ Constants.MAX_LENDING_DAYS);
    }

    public boolean checkout(String memberId) {
        if (this.isReferenceOnly) {
            System.out.println("This book is Reference only and can't be issued");
            return false;
        }
        return true;
    }
}
