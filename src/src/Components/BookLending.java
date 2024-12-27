package Components;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookLending {
    private Date issuedDate;
    private Date dueDate;
    private Date returnDate;
    private String barcode;
    private int memberId;
    private static List<BookLending> booksLent = new ArrayList<>();

    public BookLending(Date issuedDate, Date dueDate, Date returnDate, String barcode, int memberId) {
        this.issuedDate = issuedDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.barcode = barcode;
        this.memberId = memberId;
        BookLending.booksLent.add(this);
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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public static void lendBook(String barcode, int member_id){
        System.out.println("Book lend today: 27th December by:"+member_id);
    }
    public static BookLending fetchDetails(String barcode){
        for (BookLending bookLent : booksLent) {
            if (bookLent.barcode.equals(barcode)) {
                return bookLent; // Return the matching reservation
            }
        }
        return null;
    }
}
