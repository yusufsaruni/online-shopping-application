package harmo.productBean;

import java.io.Serializable;

public class ProductBean implements Serializable {

    private String sku;
    private String product_name;
    private String category;
    private double price;
    private String status;
    private int stock_count;

    public ProductBean(){}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getStock_count() {
        return stock_count;
    }

    public void setStock_count(int stock_count) {
        this.stock_count = stock_count;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
               "product_name='" + product_name + '\'' +
               ", category='" + category + '\'' +
               ", price='" + price + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}
