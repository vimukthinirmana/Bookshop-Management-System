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
import lk.ijse.bookshop.model.EmployeModel;
import lk.ijse.bookshop.to.Customer;
import lk.ijse.bookshop.to.Employe;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageEmployeCotroller {


    public TextField txtSearch;
    public TableView ManageEmployesTableView;
    public AnchorPane manageEmployeID;
    public TextField txtEmployeID;
    public TextField txtEmployeName;
    public TextField txtEmployePhoneNo;
    public TextField txtEmployeAddress;
    public TextField txtEmployeRoleType;
    public TextField txtEmployeSalary;
    public TableColumn tbcEmployeID;
    public TableColumn tbcName;
    public TableColumn tbcRoleType;
    public TableColumn tbcAddress;
    public TableColumn tbcSalary;
    public TableColumn tbcPhoneNo;

    public void addEmployeBtnOnAction(ActionEvent actionEvent) {
        String employeId = txtEmployeID.getText();
        String name = txtEmployeName.getText();
        String address = txtEmployeAddress.getText();
        int phoneNo = Integer.parseInt(txtEmployePhoneNo.getText());
        String roleType = txtEmployeRoleType.getText();
        double salary = Double.parseDouble(txtEmployeSalary.getText());

        Employe employe = new Employe(employeId,name,address,phoneNo,roleType,salary);
        try {
            boolean isAdded = EmployeModel.add(employe);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employe Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllEmployes();
    }

    public void deleteEmployeBtnOnAction(ActionEvent actionEvent) {
        String employeId = txtEmployeID.getText();


        Employe employe = new Employe(employeId);
        try {
            boolean isDelete = EmployeModel.delete(employe,employeId);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employe Deleted!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllEmployes();
    }

    public void updateEmployeBtnOnAction(ActionEvent actionEvent) {
        String employeId = txtEmployeID.getText();
        String name = txtEmployeName.getText();
        String address = txtEmployeAddress.getText();
        int phoneNo = Integer.parseInt(txtEmployePhoneNo.getText());
        String roleType = txtEmployeRoleType.getText();
        double salary = Double.parseDouble(txtEmployeSalary.getText());

        Employe employe = new Employe(employeId,name,address,phoneNo,roleType,salary);
        try {
            boolean isUpdated = EmployeModel.update(employe,employeId);

            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employe updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllEmployes();
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
        String employeId = txtSearch.getText();
        try {
            Employe employe = EmployeModel.search(employeId);
            if(employe != null) {
                fillData(employe);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillData(Employe employe) {
        txtEmployeID.setText(employe.getEmployeId());
        txtEmployeName.setText(employe.getName());
        txtEmployeAddress.setText(employe.getAddress());
        txtEmployePhoneNo.setText(String.valueOf(employe.getPhoneNo()));
        txtEmployeRoleType.setText(employe.getRoleType());
        txtEmployeSalary.setText(String.valueOf(employe.getSalary()));
    }

    public void initialize()  {
        tbcEmployeID.setCellValueFactory(new PropertyValueFactory<>("employeId"));
        tbcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbcAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tbcPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        tbcRoleType.setCellValueFactory(new PropertyValueFactory<>("roleType"));
        tbcSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        loadAllEmployes();
    }

    private void loadAllEmployes() {
        ObservableList<Employe> EList = FXCollections.observableArrayList();

        try {
            ArrayList<Employe> employeData = EmployeModel.getEmployeData();

            for (Employe st : employeData) {

                Employe s = new Employe(st.getEmployeId(), st.getName(), st.getAddress(), st.getPhoneNo(),st.getRoleType(),st.getSalary());
                EList.add(s);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        ManageEmployesTableView.setItems(EList);
    }

}
