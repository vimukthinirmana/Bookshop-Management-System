package lk.ijse.bookshop.to;

import java.time.LocalDate;

public class Order {
    private String orderId;
    private LocalDate date;
    private String nic;

    public Order() {
    }

    public Order(String orderId, LocalDate date, String nic) {
        this.orderId = orderId;
        this.date = date;
        this.nic = nic;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }





  @Override
    public String toString() {
      return "Order{" +
              "orderId='" + orderId + '\'' +
              ", date='" + date + '\'' +
              ", nic='" + nic + '\'' +
              '}';
  }

}
