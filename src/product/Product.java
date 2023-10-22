
package product;

import java.text.DecimalFormat;

public class Product {

    private Integer ID;
    private String productName;
    private Double price;

    public Product() {
    }

    public Product(Integer ID, String productName, Double price) {
        this.ID = ID;
        this.productName = productName;
        this.price = price;
    }

    public Product(Product original) {
        ID = original.ID;
        productName = original.productName;
        price = original.price;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setProductName(String name) {
        productName = name;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductPrice(Double price) {
        this.price = price;
    }

    public double getProductPrice() {
        return price;
    }

    public Product getProduct() {
        return new Product(this);
    }

    public String toString() {
        DecimalFormat dF = new DecimalFormat("#.##");
        return "Product|" + "ID: " + ID + ", " + "Name: " + productName + ", " + "Price: " + dF.format(price);
    }

    public boolean equalsProduct(Product otherProduct) {
        if (otherProduct == null) {
            return false;
        } else {
            return ID.equals(otherProduct.ID) && productName.equals(otherProduct.productName) && price.equals(otherProduct.price);
        }
    }

}