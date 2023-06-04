package lk.ijse.bookshop.to;

public class Book {
    private String bookId;
   private String name;
    private String category;
    private int year;
    private double price;
    private int QtyOnStock;
    private String employeId;

    public Book (String bookId){
    }

    public Book(String bookId, String name, String category, int year,double price ,int QtyOnStock, String employeId) {
        this.bookId = bookId;
        this.name = name;
        this.category = category;
        this.year = year;
        this.price = price;
        this.QtyOnStock = QtyOnStock;
        this.employeId = employeId;
    }




    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }



    public int getQtyOnStock() {
        return QtyOnStock;
    }

    public void setQtyOnStock(int qtyOnStock) {
        this.QtyOnStock = qtyOnStock;
    }
    public String getEmployeId() {
        return employeId;
    }

    public void setEmployeId(String employeId) {
        this.employeId = employeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", year='" + year+ '\'' +
                ", price='" + price + '\'' +
                ", QtyOnStock='" + QtyOnStock + '\'' +
                ", employeId=" + employeId +
                '}';
    }
}
