package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bookshop.model.BookModel;
import lk.ijse.bookshop.model.CustomerModel;
import lk.ijse.bookshop.model.OrderModel;
import lk.ijse.bookshop.model.PlaceOrderModel;
import lk.ijse.bookshop.to.Book;
import lk.ijse.bookshop.to.CartDetail;
import lk.ijse.bookshop.to.Customer;
import lk.ijse.bookshop.to.PlaceOrder;
import lk.ijse.bookshop.view.tm.PlaceOrderTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {


    public Label lblOrderId;
    public Label lblOrderDate;
    public JFXComboBox cmbCustomerId;
    public Label lblCustomerName;
    public JFXComboBox cmbIBookID;
    public Label lblDescription;
    public Label lblUnitPrice;
    public Label lblQtyOnHand;
    public TextField txtQty;

    @FXML
    private TableView<PlaceOrderTM> tblOrderCart;

    @FXML
    private TableColumn<?, ?> colIBookId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colAction;

    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadOrderDate();
        loadCustomerIds();
        loadNextOrderId();
        loadItemCodes();
        setCellValueFactory();
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        String bookId= String.valueOf(cmbIBookID.getValue());
        String name = lblDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.valueOf(lblUnitPrice.getText());
        double total = unitPrice * qty;
        Button btnDelete = new Button("Delete");

        txtQty.setText("");

        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colIBookId.getCellData(i).equals(bookId)) {
                    /*qty += (int) colQty.getCellData(i);*/
                    String cQty=String.valueOf(colQty.getCellData(i));
                    qty += Integer.parseInt(cQty);
                    total = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);
                    tblOrderCart.refresh();
                    return;
                }
            }
        }

        /* set delete button to some action before it put on obList */
        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(no) == ok) {
                PlaceOrderTM tm = tblOrderCart.getSelectionModel().getSelectedItem();
                /*
                netTot = Double.parseDouble(txtNetTot.getText());
                netTot = netTot - tm.getTotalPrice();
                */

                tblOrderCart.getItems().removeAll(tblOrderCart.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new PlaceOrderTM(bookId, name, qty, unitPrice, total, btnDelete));
        tblOrderCart.setItems(obList);

    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        String orderId = lblOrderId.getText();
        String nic = String.valueOf(cmbCustomerId.getValue());

        ArrayList<CartDetail> cartDetails = new ArrayList<>();

        /* load all cart items' to orderDetails arrayList */
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the orderDetails */
            PlaceOrderTM tm = obList.get(i);
            cartDetails.add(new CartDetail(orderId, tm.getBookId(), tm.getQty(), tm.getName(), tm.getUnitPrice()));
        }

        PlaceOrder placeOrder = new PlaceOrder(nic, orderId, cartDetails);
        try {
            boolean isPlaced = PlaceOrderModel.placeOrder(placeOrder);
            if (isPlaced) {
                /* to clear table */
                obList.clear();
                loadNextOrderId();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadNextOrderId() {
        try {
            String orderId = OrderModel.generateNextOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadCustomerIds() {

        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = CustomerModel.loadCustomerIds();

            for (String nic : idList) {
                observableList.add(nic);
            }
            cmbCustomerId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = BookModel.loadItemCodes();

            for (String bookId : itemList) {
                observableList.add(bookId);
            }
            cmbIBookID.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colIBookId.setCellValueFactory(new PropertyValueFactory("bookId"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
    }


    public void cmbBookOnAction(ActionEvent actionEvent) {
        String bookId = String.valueOf(cmbIBookID.getValue());


        try {
            Book book = BookModel.search(bookId);
            fillItemFields(book);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(Book book) {
        lblDescription.setText(book.getName());
        lblUnitPrice.setText(String.valueOf(book.getPrice()));
        lblQtyOnHand.setText(String.valueOf(book.getQtyOnStock()));
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        btnAddToCartOnAction(actionEvent);
    }


    public void cmbCustoerNicOnAction(ActionEvent actionEvent) {
        String nic = String.valueOf(cmbCustomerId.getValue());


        try {
            Customer customer = CustomerModel.search(nic);
            fillCustomerFields(customer);

        } catch (SQLException | ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }
    }
    private void fillCustomerFields(Customer customer) {
        lblCustomerName.setText(customer.getName());

    }
}
