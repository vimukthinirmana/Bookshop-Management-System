package lk.ijse.bookshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bookshop.model.EmployeModel;
import lk.ijse.bookshop.model.SupplierModel;
import lk.ijse.bookshop.to.Employe;
import lk.ijse.bookshop.to.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SuplierManageFormController {
    public TextField txtSupplierID;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtPhoneNo;
    public TableView manageSupplierTableView;
    public TextField txtEmployeID;
    public TextField txtSearch;
    public TableColumn tbcSupplierID;
    public TableColumn tbcName;
    public TableColumn tbcEmail;
    public TableColumn tbcPhoneNo;
    public TableColumn tbcEmployeID;

    public void addSupplierBtnOnAction(ActionEvent actionEvent) {
        String supplierId = txtSupplierID.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        int phoneNo = Integer.parseInt(txtPhoneNo.getText());
        String employeId = txtEmployeID.getText();


        Supplier supplier = new Supplier(supplierId,name,email,phoneNo,employeId);
        try {
            boolean isAdded = SupplierModel.add(supplier);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllSuppliers();
    }

    public void deleteSupplierBtnOnAction(ActionEvent actionEvent) {
        String supplierId = txtSupplierID.getText();


        Supplier supplier = new Supplier(supplierId);
        try {
            boolean isDelete = SupplierModel.delete(supplier,supplierId);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllSuppliers();
    }

    public void updateSupplierBtnOnAction(ActionEvent actionEvent) {
        String supplierId = txtSupplierID.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        int phoneNo = Integer.parseInt(txtPhoneNo.getText());
        String employeId = txtEmployeID.getText();

        Supplier supplier = new Supplier(supplierId,name,email,phoneNo,employeId);
        try {
            boolean isUpdated = SupplierModel.update(supplier,supplierId);

            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllSuppliers();
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
        String supplierId = txtSupplierID.getText();
        try {
            Supplier supplier = SupplierModel.search(supplierId);
            if(supplier != null) {
                fillData(supplier);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Supplier supplier) {
        txtSupplierID.setText(supplier.getSupplierId());
        txtName.setText(supplier.getName());
        txtEmail.setText(supplier.getEmail());
        txtPhoneNo.setText(String.valueOf(supplier.getPhoneNo()));
        txtEmployeID.setText(supplier.getEmployeId());
    }

    public void initialize()  {
        tbcSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tbcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbcPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        tbcEmployeID.setCellValueFactory(new PropertyValueFactory<>("employeId"));


        loadAllSuppliers();
    }

    private void loadAllSuppliers() {
        ObservableList<Supplier> SList = FXCollections.observableArrayList();

        try {
            ArrayList<Supplier> supplierData = SupplierModel.getSupplierData();

            for (Supplier st : supplierData) {

                Supplier s = new Supplier(st.getSupplierId(), st.getName(), st.getEmail(), st.getPhoneNo(),st.getEmployeId());
                SList.add(s);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        manageSupplierTableView.setItems(SList);
    }


}
