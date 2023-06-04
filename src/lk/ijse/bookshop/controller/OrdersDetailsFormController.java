package lk.ijse.bookshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.model.BookModel;
import lk.ijse.bookshop.model.OrderDetailModel;
import lk.ijse.bookshop.model.OrderDetailsModel;
import lk.ijse.bookshop.to.Book;
import lk.ijse.bookshop.to.OrderDetails;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrdersDetailsFormController {
    public TableView orderDetailsTableView;
    public TableColumn tbOrderId;
    public TableColumn tbBookId;
    public TableColumn tbQty;
    public TableColumn tbUnitPrice;

    public void initialize()  {
        tbOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tbBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        tbQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        tbUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));

        loadAllOderDetails();
    }

    private void loadAllOderDetails() {
        ObservableList<OrderDetails> bList = FXCollections.observableArrayList();

        try {
            ArrayList<OrderDetails> OrderDetailsData = OrderDetailsModel.getBookData();

            for (OrderDetails st : OrderDetailsData) {

                OrderDetails b = new OrderDetails(st.getOrderId(), st.getBookId(), st.getQty(), st.getUnitPrice());
                bList.add(b);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        orderDetailsTableView.setItems(bList);
    }

    public void genarateReporsBtnOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/bookshop/report/orderDetails.jrxml");

        /*HashMap is something like key-value pairing data storing mechanism*/
        HashMap<String, Object> hm = new HashMap<>();
       /* hm.put("cashier_name", txtCashier.getText());
        hm.put("table_no", Integer.valueOf(txtTblNo.getText()));*/

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            /*if you haven't any parameters(HashMap reference) to pass, then put null for second argument*/
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint, true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
