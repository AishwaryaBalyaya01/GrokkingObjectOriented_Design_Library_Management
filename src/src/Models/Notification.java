package Models;

import java.util.Date;

public class Notification {
    private String message;
    private Date created_Date;

    public Notification( String message) {
        this.message = message;
        this.created_Date = new java.sql.Date(System.currentTimeMillis());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(Date created_Date) {
        this.created_Date = created_Date;
    }
    public void sendNotification(){
        System.out.println(message);
    }
}
