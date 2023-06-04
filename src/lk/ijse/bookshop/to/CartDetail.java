package lk.ijse.bookshop.to;

public class CartDetail {
    private String orderId;
    private String bookId;
    private int qty;

    private double unitPrice;

    private String name;

    private String category;

    private int year;

    public CartDetail() {
    }
    public CartDetail(String orderId, String bookId, int qty, String name, double unitPrice) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.qty = qty;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public CartDetail(String orderId, String bookId,int qty, double unitPrice,int year,String name,String category) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.name = name;
        this.category = category;
        this.year = year;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "orderId='" + orderId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", qty='" + qty + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", year='" + year + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
