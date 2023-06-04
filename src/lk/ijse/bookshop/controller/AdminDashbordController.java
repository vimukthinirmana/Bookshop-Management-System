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
import java.util.Date;

public class AdminDashbordController {

    public Label dateID;

    public AnchorPane contex;

    public Label timeT1;
    public AnchorPane contextAuthonticationPane;
    public AnchorPane adminDashbordContex;

    public void initialize(){
        LocalDate date = LocalDate.now();
        dateID.setText(String.valueOf(date));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO,event -> {
            DateTimeFormatter dt=DateTimeFormatter.ofPattern("HH:mm:ss");
            timeT1.setText(LocalTime.now().format(dt));
        }),new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }




    public void customerDetailsBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Customer,contex);
    }



    public void authorsBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.AuthorsManage,contex);
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

    public void publisherBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PublishersManage,contex);
    }

    public void salesBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Sales,contex);
    }

    public void reportsBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Reports,contex);
    }



    public void ordersDetailsBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.OrderDetails,contex);
    }

    public void ManageBookBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ManageBook,contex);
    }

    public void ManageEployeBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ManageEmploye,contex);
    }

    public void bookSupplyDetailsBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.BookSupplieDetails,contex);
    }



    public void manageSupplierBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SupplierManage,contex);
    }

    public void dashbordBtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/bookshop/view/AdminDashbord.fxml"));
        Stage stage = new Stage();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Admin Dashbord");
        stage.setScene(scene);
        stage.show();
    }
}
