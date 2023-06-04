package lk.ijse.bookshop.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;
    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {

            case ManageBook:
                window.setTitle("Manage Book Form");
                initUI("ManageBookForm.fxml");
                break;
            case ManageEmploye:
                window.setTitle("Manage Employe Form");
                initUI("ManageEmployeForm.fxml");
                break;

            case PublishersManage:
                window.setTitle("Publishers ManageForm Form");
                initUI("PublishersManageForm.fxml");
                break;
            case SupplierManage:
                window.setTitle("Suplier Manage Form");
                initUI("SuplierManageForm.fxml");
                break;
            case Sales:
                window.setTitle("Sales Form");
                initUI("SalesForm.fxml");
                break;

            case BookDetails:
                window.setTitle("BooksDetails Form");
                initUI("BooksDetailsForm.fxml");
                break;
            case Reports:
                window.setTitle("Reports Form");
                initUI("ReportsForm.fxml");
                break;
            case OrderDetails:
                window.setTitle("Orders Details Form");
                initUI("OrdersDetailsForm.fxml");
                break;
            case Catogery:
                window.setTitle("Catogery Form");
                initUI("ManageCatogetyForm.fxml");
                break;
            case Customer:
                window.setTitle("Customer Form");
                initUI("ManageCustomerForm.fxml");
                break;
            case AuthorsManage:
                window.setTitle("Authors Manage Form");
                initUI("AuthorsManageForm.fxml");
                break;

            case plaseOrder:
                window.setTitle("PlaceOrder Form");
                initUI("PlaceOrderForm.fxml");
                break;
            case orderDetails:
                window.setTitle("OrdersDetails Form");
                initUI("OrdersDetailsForm.fxml");
                break;
            case BookSupplieDetails:
                window.setTitle("Books Supplie Details Form");
                initUI("BookSupplieDetails.fxml");
                break;







            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                        .getResource("/lk/ijse/bookshop/view/" + location)));
    }
}
