package lk.ijse.bookshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookshop.model.CustomerModel;
import lk.ijse.bookshop.to.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFormController {
    public TextField txtCustomerNic;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TableView ManageCustomersTableView;
    public TextField txtPhoneNo;
    public TextField txtSearch;
    public AnchorPane manageCustomersPaneID;
    public TableColumn tbCustomerNic;
    public TableColumn tbCustomerName;
    public TableColumn tbAddress;
    public TableColumn tbPhoneNo;
    public TextField txtEployeID;
    public TableColumn tbEmployeID;

    public void addCustomerBtnOnAction(ActionEvent actionEvent) {
        String nic = txtCustomerNic.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        int phoneNo = Integer.parseInt(txtPhoneNo.getText());
        String employeId = txtEployeID.getText();

        Customer customer = new Customer(nic,name,address,phoneNo,employeId);
        try {
            boolean isAdded = CustomerModel.add(customer);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        lordAllCustomers();
    }

    public void deleteCustomerBtnOnAction(ActionEvent actionEvent) {
        String nic = txtCustomerNic.getText();


        Customer customer = new Customer(nic);
        try {
            boolean isDelete = CustomerModel.delete(customer,nic);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        lordAllCustomers();
    }

    public void updateCustomerBtnOnAction(ActionEvent actionEvent) {
        String nic = txtCustomerNic.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        int phoneNo = Integer.valueOf(txtPhoneNo.getText());
        String employeId =txtEployeID.getText();

        Customer customer = new Customer(nic,name,address,phoneNo,employeId);
        try {
            boolean isUpdated = CustomerModel.update(customer,nic);

            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        lordAllCustomers();
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();
        try {
            Customer customer = CustomerModel.search(id);
            if(customer != null) {
                fillData(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Customer customer) {
        txtCustomerNic.setText(customer.getNic());
        txtCustomerName.setText(customer.getName());
        txtCustomerAddress.setText(customer.getAddress());
        txtPhoneNo.setText(String.valueOf(customer.getPhoneNo()));
        txtEployeID.setText(customer.getEmployeId());
    }
    public void initialize(){
        tbCustomerNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tbCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tbPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        tbEmployeID.setCellValueFactory(new PropertyValueFactory<>("employeId"));



        lordAllCustomers();
    }
    private void lordAllCustomers(){
        ObservableList<Customer>clist = FXCollections.observableArrayList();

        try {
            ArrayList<Customer>customerData = CustomerModel.getCustomerData();
            for (Customer st : customerData){
                Customer s = new Customer(st.getNic(),st.getName(),st.getAddress(),st.getPhoneNo(),st.getEmployeId());
                clist.add(s);
            }
        }catch (SQLException e){
            System.out.println("Sql");
        }catch (ClassNotFoundException e){
            System.out.println("class");
        }
        ManageCustomersTableView.setItems(clist);
    }

}
