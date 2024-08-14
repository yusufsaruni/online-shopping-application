package harmo.userBean;

public class Cart {

    private long serial_number;

    private String product_name;
    private double product_price;
    private int quantity;
    private double amount;
    public Cart(){
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(long serial_number) {
        this.serial_number = serial_number;
    }
}
