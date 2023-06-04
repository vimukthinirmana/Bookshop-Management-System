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
import lk.ijse.bookshop.model.BookModel;
import lk.ijse.bookshop.to.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageBookController {

    public TextField txtSearch;
    public TextField txtBookCategory;
    public TextField txtBookQtyOnStock;
    public TableView manageBookTableView;
    public TextField txtBookPrice;
    public TextField txtBookYear;
    public TextField txtBookName;
    public TextField txtBookID;
    public AnchorPane manageBooksID;
    public TableColumn tbcBookID;
    public TableColumn tbcName;
    public TableColumn tbcCategory;
    public TableColumn tbcYear;
    public TableColumn tbcQtyOnStock;
    public TableColumn tbcPrice;
    public TextField txtEmployeID;
    public TableColumn tbcEployeId;

    public TextField getTxtBookID() {
        return txtBookID;
    }

    public TextField getTxtBookName() {

        return txtBookName;
    }

    public TextField getTxtBookCategory() {
        return txtBookCategory;
    }


//-------------------------------------------------------------------------------------
    public void addBookBtnOnAction(ActionEvent actionEvent) {

            String bookID = txtBookID.getText();
            String name = txtBookName.getText();
            String category  = txtBookCategory.getText();
            int year = Integer.parseInt(txtBookYear.getText());
            double price  = Double.parseDouble(txtBookPrice.getText());
            int QtyOnStock = Integer.parseInt(txtBookQtyOnStock.getText());
            String employeId = txtEmployeID.getText();

            Book book = new Book(bookID,name,category, year, price,QtyOnStock,employeId);
            try {
                boolean isAdded = BookModel.add(book);

                if(isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Book Added!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            loadAllBooks();

    }

    public void deleteBookBtnOnAction(ActionEvent actionEvent) {

        String bookId = txtBookID.getText();

        Book book = new Book(bookId);
        try {
            boolean isDelete = BookModel.delete(book,bookId);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Book Deleted!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllBooks();

        if (txtBookID.getText().isEmpty()){
            txtBookID.setPromptText("enter BookID");
        }
    }

    public void updateBookBtnOnAction(ActionEvent actionEvent) {
        String bookId = txtBookID.getText();
        String name = txtBookName.getText();
        String category = txtBookCategory.getText();
        int year = Integer.parseInt(txtBookYear.getText());
        double price = Double.parseDouble(txtBookPrice.getText());
        int QtyOnStock = Integer.parseInt(txtBookQtyOnStock.getText());
        String employeId = txtEmployeID.getText();

        Book book = new Book(bookId,name,category, year, price,QtyOnStock,employeId);
        try {
            boolean isUpdated = BookModel.update(book,bookId);

            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Book updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllBooks();
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
        String bookId = txtSearch.getText();
        try {
            Book book = BookModel.search(bookId);
            if(book != null) {
                fillData(book);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillData(Book book) {
        txtBookID.setText(book.getBookId());
        txtBookName.setText(book.getName());
        txtBookCategory.setText(book.getCategory());
        txtBookYear.setText(String.valueOf(book.getYear()));
        txtBookPrice.setText(String.valueOf(book.getPrice()));
        txtBookQtyOnStock.setText(String.valueOf(book.getQtyOnStock()));
        txtEmployeID.setText(book.getEmployeId());


    }
    public void initialize()  {
        tbcBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tbcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbcCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tbcYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        tbcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tbcQtyOnStock.setCellValueFactory(new PropertyValueFactory<>("QtyOnStock"));
        tbcEployeId.setCellValueFactory(new PropertyValueFactory<>("employeId"));

        loadAllBooks();
    }

    private void loadAllBooks() {
        ObservableList<Book> bList = FXCollections.observableArrayList();

        try {
            ArrayList<Book> bookData = BookModel.getBookData();

            for (Book st : bookData) {

                Book b = new Book(st.getBookId(), st.getName(), st.getCategory(), st.getYear(),st.getPrice(),st.getQtyOnStock(),st.getEmployeId());
                bList.add(b);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        manageBookTableView.setItems(bList);
    }
}
