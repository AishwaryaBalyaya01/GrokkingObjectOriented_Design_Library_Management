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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member extends Account{
    private int totalBooks;
    private Date membershipDate;
    private static List<Member> members = new ArrayList<>();

    public Member(int id, String password, String status, Person person) {
        super(id, password, status, person);
        this.membershipDate = new java.sql.Date(System.currentTimeMillis());
        Member.members.add(this);
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
        if(bookItem.isReferenceOnly()){
            System.out.println(bookItem.getTitle()+" is for reference only");
            return false;
        }
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if(bookReservation!=null && bookReservation.getMember_id()!=this.getId()) {
            System.out.println(bookItem.getTitle()+" is reserved by someone else");
            return false;
        }
        else if(bookReservation!=null){
            bookReservation.updateStatus(ReservationStatus.COMPLETED);
        }
        this.incrementBooksCount();
        System.out.println(bookItem.getTitle()+" Checked Out by "+this.getPerson().getName());
        BookLending bookLending = new BookLending(new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()),new java.sql.Date(System.currentTimeMillis()), bookItem.getBarcode(), this.getId());
        return true;
    }

    public void renewBook(BookItem bookItem){
        this.checkForFine(bookItem.getBarcode());
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if(bookReservation!=null && bookReservation.getMember_id()!=this.getId()){
            Member member = Member.fetchMemberDetails(bookReservation.getMember_id());
            System.out.println("Book is reserved by other member");
            this.decrementBooksCount();
            bookItem.updateBookItemState(BookStatus.RESERVED);
            bookReservation.sendNotification("Book is available to "+member.getPerson().getName());
        }
        else if(bookReservation!=null){
            bookReservation.updateStatus(ReservationStatus.COMPLETED);
        }
        BookLending.lendBook(bookItem.getBarcode(),this.getId());
        bookItem.updateDueDate(new java.sql.Date(System.currentTimeMillis()));
        System.out.println(bookItem.getTitle()+" Renewed by "+this.getPerson().getName());
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
        Member member = Member.fetchMemberDetails(bookReservation.getMember_id());
        if(bookReservation!=null){
            bookItem.updateBookItemState(BookStatus.RESERVED);
            bookReservation.sendNotification("Book is available to "+member.getPerson().getName());
        }
        bookItem.updateBookItemState(BookStatus.AVAILABLE);
        System.out.println(bookItem.getTitle()+" returned by "+this.getPerson().getName());
    }

    public void reserveBookItem(BookItem bookItem){
        BookReservation bookReservation = new BookReservation(bookItem.getBarcode(),this.getId(),new java.sql.Date(System.currentTimeMillis()), ReservationStatus.COMPLETED);
        System.out.println(bookItem.getTitle()+" reserved by "+this.getPerson().getName());
    }

    public static Member fetchMemberDetails(int id){
        for (Member member : members) {
            if (member.getId()==id) {
                return member; // Return the matching reservation
            }
        }
        return null;
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
