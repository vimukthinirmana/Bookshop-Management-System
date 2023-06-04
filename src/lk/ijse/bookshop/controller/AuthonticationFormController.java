package lk.ijse.bookshop.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.naming.ldap.PagedResultsControl;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AuthonticationFormController {


    public TextField txtUserName;
    public TextField txtPassword;
    public JFXButton loginBtn;
    public AnchorPane contextAuthonticationPane;




    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException {


        if (txtUserName.getText().equals("admin") || txtPassword.getText().equals(1234)){
            Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/bookshop/view/AdminDashbord.fxml"));
            Stage stage = new Stage();
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Admin Dashbord");
            stage.setScene(scene);
            stage.show();


        }else if (txtUserName.getText().equals("cashier") || txtPassword.getText().equals(1234)) {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/bookshop/view/CashierDashboard.fxml"));
            Stage stage = new Stage();
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Cashier Dashbord");
            stage.setScene(scene);
            stage.show();


        } else{
            txtUserName.setStyle("-fx-text-box-border:red;-fx-focus-color:red;");
            txtUserName.setPromptText("enter user name");
            txtPassword.setStyle("-fx-text-box-border:red;-fx-focus-color:red;");
            txtPassword.setPromptText("enter user password");
        }


    }


}

