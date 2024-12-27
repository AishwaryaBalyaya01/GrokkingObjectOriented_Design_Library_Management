package Models;

public class EmailNotification extends Notification{
    private String email;
    public EmailNotification( String message, String email) {
        super(message);
        this.email = email;
    }
    @Override
    public  void sendNotification(){
        System.out.println(super.getMessage()+" on "+email);
    }
}
