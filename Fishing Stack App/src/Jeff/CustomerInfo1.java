package Jeff;

import javafx.beans.property.*;

public class CustomerInfo1 {
    //private final SimpleStringProperty date;
    private final SimpleIntegerProperty customerID;
    private final SimpleStringProperty productID;
    private final SimpleStringProperty productName;
    private final SimpleDoubleProperty productPrice;
    private final SimpleIntegerProperty quantity;
    private final SimpleDoubleProperty subtotal;

    public CustomerInfo1( Integer id, String productid, String productname, Double price, Integer qty, Double subttl) {
        //this.date = new SimpleStringProperty(dateOfpur);
        this.customerID = new SimpleIntegerProperty(id);
        this.productID = new SimpleStringProperty(productid);
        this.productName = new SimpleStringProperty(productname);
        this.productPrice = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(qty);
        this.subtotal = new SimpleDoubleProperty(subttl);
    }


    //public String getDate() { return date.get(); }

    public Integer getCustomerId() {
        return customerID.get();
    }

    public String getProductId() {
        return productID.get();
    }

    public String getProductName() {
        return productName.get();
    }

    public Double getProductPrice() {
        return productPrice.get();
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public Double getSubTotal() {
        return subtotal.get();
    }

    //public void setDate(String dateval) {date.set(dateval);}

    public void setCustomerID(int userid) {
        customerID.set(userid);
    }

    public void setProductID(String proid) {
        productID.set(proid);
    }

    public void setProductName(String proname) {
        productName.set(proname);
    }

    public void setProductPrice(Double price) {
        productPrice.set(price);
    }

    public void setQuantity(int qty) {
        quantity.set(qty);
    }

    public void setSubtotal(double stotal) {
        subtotal.set(stotal);
    }

    //public StringProperty dateProperty(){return date;}
    public IntegerProperty customerIDProperty(){
        return customerID;
    }
    public StringProperty productIDProperty(){
        return productID;
    }
    public StringProperty productNameProperty(){
        return productName;
    }
    public DoubleProperty productPriceProperty(){
        return productPrice;
    }
    public IntegerProperty quantityProperty(){
        return quantity;
    }
    public DoubleProperty subtotalProperty(){
        return subtotal;
    }


}
