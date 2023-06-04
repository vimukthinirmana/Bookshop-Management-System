package lk.ijse.bookshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bookshop.model.AuthorModel;
import lk.ijse.bookshop.model.EmployeModel;
import lk.ijse.bookshop.to.Author;
import lk.ijse.bookshop.to.Employe;

import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorsManageFormController {
    public TableColumn tbAuthorID;
    public TableColumn tbName;
    public TableColumn tbPhoneNo;
    public TableColumn tbEmail;
    public TableView authorManageTableView;
    public TextField txtSearch;
    public TextField txtEmail;
    public TextField txtPhoneNo;
    public TextField txtName;
    public TextField txtAuthorID;

    public void addAuthorBtnOnAction(ActionEvent actionEvent) {
        String authorId = txtAuthorID.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        int phoneNo = Integer.parseInt(txtPhoneNo.getText());



        Author author = new Author(authorId,name,email,phoneNo);
        try {
            boolean isAdded = AuthorModel.add(author);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Author Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllAuthors();
    }

    public void deleteAuthorBtnOnAction(ActionEvent actionEvent) {
        String authorId = txtAuthorID.getText();


        Author author = new Author(authorId);
        try {
            boolean isDelete = AuthorModel.delete(author,authorId);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Author Deleted!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllAuthors();
    }

    public void updateAuthorBtnOnAction(ActionEvent actionEvent) {
        String authorId = txtAuthorID.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        int phoneNo = Integer.parseInt(txtPhoneNo.getText());

        Author author = new Author(authorId,name,email,phoneNo);

        try {
            boolean isUpdated = AuthorModel.update(author,authorId);

            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Author updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllAuthors();
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
        String authorId = txtSearch.getText();
        try {
            Author author = AuthorModel.search(authorId);
            if(author != null) {
                fillData(author);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void fillData(Author author) {
        txtAuthorID.setText(author.getAuthorId());
        txtName.setText(author.getName());
        txtEmail.setText(author.getEmail());
        txtPhoneNo.setText(String.valueOf(author.getPhoneNo()));
        ;
    }

    public void initialize()  {
        tbAuthorID.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        tbName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));


        loadAllAuthors();
    }

    private void loadAllAuthors() {
        ObservableList<Author> AList = FXCollections.observableArrayList();

        try {
            ArrayList<Author> authorData = AuthorModel.getAuthorData();

            for (Author st : authorData) {

                Author s = new Author(st.getAuthorId(), st.getName(), st.getEmail(), st.getPhoneNo());
                AList.add(s);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        authorManageTableView.setItems(AList);
    }


}
