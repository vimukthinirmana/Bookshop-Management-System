package lk.ijse.bookshop.view.tm;

import javafx.scene.control.Button;

public class PlaceOrderTM {
    private String bookId;
    private String name;
    private int qty;
    private double unitPrice;
    private double total;

    private Button btnDelete;

    public PlaceOrderTM() {
    }

      public PlaceOrderTM(String bookId, String name, int qty, double unitPrice, double total, Button btnDelete) {
        this.bookId = bookId;
        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
        this.btnDelete = btnDelete;

    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }



    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }







   @Override
    public String toString() {
        return "PlaceOrderTM{" +
                "bookId='" + bookId + '\'' +
                ", name='" + name + '\'' +
                ", qty='" + qty + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", total='" + total + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
