package lk.ijse.bookshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bookshop.model.BookModel;
import lk.ijse.bookshop.to.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class BooksDetailsFormController {


    public TableView manageBookTableView;
    public TableColumn tbcBookID;
    public TableColumn tbcName;
    public TableColumn tbcCategory;
    public TableColumn tbcYear;
    public TableColumn tbcPrice;
    public TableColumn tbcQtyOnStock;
    public TableColumn tbcEployeId;

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
