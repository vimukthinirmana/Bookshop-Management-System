package lk.ijse.bookshop.to;

public class OrderDetails {
    private String orderId;
    private String bookId;
    private int qty;
    private double unitPrice;

    public OrderDetails(){}
    public OrderDetails(String orderId,String bookId,int qty,double unitPrice){
        this.orderId = orderId;
        this.bookId = bookId;
        this.qty = qty;
        this.unitPrice = unitPrice;

    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId='" + orderId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", qty='" + qty + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }

}
