package lk.ijse.bookshop.to;

import java.util.ArrayList;

public class PlaceOrder {
    private String nic;
    private String orderId;
    private ArrayList<CartDetail> orderDetails = new ArrayList<>();

    public PlaceOrder() {
    }

    public PlaceOrder(String nic, String orderId, ArrayList<CartDetail> orderDetails) {
        this.nic = nic;
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ArrayList<CartDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<CartDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }





   @Override
    public String toString() {
        return "PlaceOrder{" +
                "nic ='" + nic + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
