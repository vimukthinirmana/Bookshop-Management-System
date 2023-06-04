package lk.ijse.bookshop.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CashierDashboardController {
    public AnchorPane cashierContex;
    public Label dateID;
    public Label timeT1;

    public void initialize(){
        LocalDate date = LocalDate.now();
        dateID.setText(String.valueOf(date));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            DateTimeFormatter dt=DateTimeFormatter.ofPattern("HH:mm:ss");
            timeT1.setText(LocalTime.now().format(dt));
        }),new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void booksDetailsBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.BookDetails,cashierContex);
    }

    public void manageCustomersBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Customer,cashierContex);
    }

    public void logoutBtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/bookshop/view/AuthonticationForm.fxml"));
        Stage stage = new Stage();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Authontication Form");
        stage.setScene(scene);
        stage.show();
    }



    public void placeOrderBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.plaseOrder,cashierContex);
    }

    public void orderDetailsBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.OrderDetails,cashierContex);
    }

    public void DashboardBtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/bookshop/view/CashierDashboard.fxml"));
        Stage stage = new Stage();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Cashier Dashbord");
        stage.setScene(scene);
        stage.show();
    }
}
