package Models;

public class Rack {
    private int rackNumber;
    private Address location;

    public Rack(int rackNumber, Address location) {
        this.rackNumber = rackNumber;
        this.location = location;
    }

    public int getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(int rackNumber) {
        this.rackNumber = rackNumber;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }
}
