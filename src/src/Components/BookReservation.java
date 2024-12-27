package Components;

import Models.EmailNotification;
import Models.Notification;
import Models.ReservationStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookReservation {
    private Date reservationDate;
    private ReservationStatus reservationStatus;
    private String barcode;
    private int member_id;
    private static List<BookReservation> reservations = new ArrayList<>();

    public BookReservation(Date reservationDate, ReservationStatus reservationStatus) {
        this.reservationDate = reservationDate;
        this.reservationStatus = reservationStatus;
        BookReservation.reservations.add(this);
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public static BookReservation fetchReservationDetails(String barcode){
        for (BookReservation reservation : reservations) {
            if (reservation.barcode.equals(barcode)) {
                return reservation; // Return the matching reservation
            }
        }
        return null;
    }
    public void updateStatus(ReservationStatus status){
        this.reservationStatus = status;
    }

    public void sendNotification(String bookIsAvailable) {
        Notification notification = new Notification(bookIsAvailable);
        notification.sendNotification();
    }
}
