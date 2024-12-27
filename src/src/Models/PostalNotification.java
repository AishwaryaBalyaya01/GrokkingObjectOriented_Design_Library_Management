package Models;

public class PostalNotification extends Notification{
    private Address address;
    public PostalNotification(String message,Address address) {
        super(message);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public  void sendNotification(){
        System.out.println(super.getMessage()+" on "+address.getStreet()+","+address.getCity()+","+address+","+address.getState()+","+address.getCountry()+","+address.getZipcode());
    }
}
