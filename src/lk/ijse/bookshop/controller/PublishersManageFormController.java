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
import lk.ijse.bookshop.model.EmployeModel;
import lk.ijse.bookshop.model.PublisherModel;
import lk.ijse.bookshop.to.Employe;
import lk.ijse.bookshop.to.Publisher;

import java.sql.SQLException;
import java.util.ArrayList;

public class PublishersManageFormController {
    public TextField txtSearch;
    public TextField txtPublisherEmail;
    public TableView ManagePublisherTableView;
    public TextField txtPublisherAddress;
    public TextField txtPublisherName;
    public TextField txtPublisherID;
    public AnchorPane publisherManagePaneID;
    public TableColumn tbcPublisherID;
    public TableColumn tbcName;
    public TableColumn tbcAddress;
    public TableColumn tbcEmail;
    public TableColumn tbcphoneNo;
    public TextField txtPublisherPhoneNo;

    public void addPublisherBtnOnAction(ActionEvent actionEvent) {
        String publisherId = txtPublisherID.getText();
        String name = txtPublisherName.getText();
        String email = txtPublisherEmail.getText();
        int phoneNo = Integer.parseInt(txtPublisherPhoneNo.getText());
        String address = txtPublisherAddress.getText();


        Publisher publisher = new Publisher(publisherId,name,email,phoneNo,address);
        try {
            boolean isAdded = PublisherModel.add(publisher);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Publisher Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllPublishers();
    }

    public void deletePublisherBtnOnAction(ActionEvent actionEvent) {
        String publisherId = txtPublisherID.getText();


        Publisher publisher = new Publisher(publisherId);
        try {
            boolean isDelete = PublisherModel.delete(publisher,publisherId);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Publisher Deleted!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllPublishers();
    }

    public void updatePublisherBtnOnAction(ActionEvent actionEvent) {
        String publisherId = txtPublisherID.getText();
        String name = txtPublisherName.getText();
        String email = txtPublisherEmail.getText();
        int phoneNo = Integer.parseInt(txtPublisherPhoneNo.getText());
        String address = txtPublisherAddress.getText();

        Publisher publisher = new Publisher(publisherId,name,email,phoneNo,address);
        try {
            boolean isUpdated = PublisherModel.update(publisher,publisherId);

            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Publisher updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllPublishers();
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
        String publisherId = txtSearch.getText();
        try {
            Publisher publisher = PublisherModel.search(publisherId);
            if(publisher != null) {
                fillData(publisher);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Publisher publisher) {
        txtPublisherID.setText(publisher.getPublisherId());
        txtPublisherName.setText(publisher.getName());
        txtPublisherEmail.setText(publisher.getEmail());
        txtPublisherPhoneNo.setText(String.valueOf(publisher.getPhoneNo()));
        txtPublisherAddress.setText(publisher.getAddress());

    }

    public void initialize()  {
        tbcPublisherID.setCellValueFactory(new PropertyValueFactory<>("publisherId"));
        tbcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbcphoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        tbcAddress.setCellValueFactory(new PropertyValueFactory<>("address"));


        loadAllPublishers();
    }

    private void loadAllPublishers() {
        ObservableList<Publisher> PList = FXCollections.observableArrayList();

        try {
            ArrayList<Publisher> publisherData = PublisherModel.getPublisherData();

            for (Publisher st : publisherData) {

                Publisher s = new Publisher(st.getPublisherId(), st.getName(), st.getEmail(), st.getPhoneNo(),st.getAddress());
                PList.add(s);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        ManagePublisherTableView.setItems(PList);
    }
}
