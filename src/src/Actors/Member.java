package Actors;

import Components.BookItem;
import Components.BookLending;
import Components.BookReservation;
import Components.Fine;
import Models.BookStatus;
import Models.Constants;
import Models.Person;
import Models.ReservationStatus;

import java.time.DateTimeException;
import java.util.Date;

public class Member extends Account{
    private int totalBooks;
    private Date membershipDate;

    public Member(int id, String password, String status, Person person) {
        super(id, password, status, person);
        this.membershipDate = new java.sql.Date(System.currentTimeMillis());
    }
    public void incrementBooksCount(){
        totalBooks++;
    }
    public void decrementBooksCount(){
        if(totalBooks>0) totalBooks--;
        else System.out.println("BookCount is 0, cant decrement");
    }

    public boolean checkoutBook(BookItem bookItem){
        if(this.getTotalBooks()>= Constants.MAX_BOOKS_ISSUED_TO_USER) {
            System.out.println("User already maxed out");
            return false;
        }
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if(bookReservation!=null && bookReservation.getMember_id()!=this.getId()) {
            System.out.println("This book is reserved by someone else");
            return false;
        }
        if(bookReservation!=null){
            bookReservation.updateStatus(ReservationStatus.COMPLETED);
        }
        this.incrementBooksCount();
        return true;
    }

    public boolean renewBook(BookItem bookItem){
        this.checkForFine(bookItem.getBarcode());
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if(bookReservation!=null && bookReservation.getMember_id()!=this.getId()){
            System.out.println("Book is reserved by other member");
            this.decrementBooksCount();
            bookItem.updateBookItemState(BookStatus.RESERVED);
            bookReservation.sendNotification("Book is available");
            return false;
        }
        else if(bookReservation!=null){
            bookReservation.updateStatus(ReservationStatus.COMPLETED);
        }
        BookLending.lendBook(bookItem.getBarcode(),this.getId());
        bookItem.updateDueDate(new java.sql.Date(System.currentTimeMillis()));
        return true;
    }

    private void checkForFine(String barcode) {
        BookLending lent = BookLending.fetchDetails(barcode);
        Date dueDate = lent.getDueDate();
        Date today = new java.sql.Date(System.currentTimeMillis());
        if(today.compareTo(dueDate)>0){
            long diff = 5;
            int days = today.getDate()-dueDate.getDate();
            Fine fine = new Fine(lent.getMemberId(), barcode);
            fine.collectFine(lent.getMemberId(),days);
        }
    }

    public void returnBook(BookItem bookItem){
        this.decrementBooksCount();
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if(bookReservation!=null){
            bookItem.updateBookItemState(BookStatus.RESERVED);
            bookReservation.sendNotification("Book is Available");
        }
        bookItem.updateBookItemState(BookStatus.AVAILABLE);
    }

    public void reserveBookItem(BookItem bookItem){
        BookReservation bookReservation = new BookReservation(new java.sql.Date(System.currentTimeMillis()), ReservationStatus.COMPLETED);
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(Date membershipDate) {
        this.membershipDate = membershipDate;
    }
}
