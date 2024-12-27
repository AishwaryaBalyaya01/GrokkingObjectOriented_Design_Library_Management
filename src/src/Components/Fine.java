package Components;

public class Fine {
    private String barcode;
    private int member_id;
    private int perDayFine;

    public Fine(int member_id, String barcode) {
        this.member_id = member_id;
        this.barcode = barcode;
        this.perDayFine = 10;
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

    public int getPerDayFine() {
        return perDayFine;
    }

    public void setPerDayFine(int perDayFine) {
        this.perDayFine = perDayFine;
    }

    public void collectFine(int id,int days){
        int fine= days*(this.perDayFine);
    }
}
