package com.example.demo2;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button but1;

    @FXML
    private PasswordField emailf;

    @FXML
    private Label lblerror;

    @FXML
    private PasswordField passwordf;

    public static String user;

    @FXML
    void initialize() {
        but1.setOnAction(actionEvent -> {
            String em = emailf.getText().trim();
            String passw = passwordf.getText().trim();

            DataBaseHU dbHandler = new DataBaseHU();
            ResultSet result = dbHandler.getUser(em, passw);

            int counter = 0;
            while(true) {
                try {
                    if (!result.next()) break; else {user = result.getString(4);}
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                counter++;
            }

            if (counter >= 1) {
                but1.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("choice.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                FadeTransition fadeTransition = new FadeTransition();
                fadeTransition.setDuration(Duration.seconds(1));
                fadeTransition.setNode(root);
                fadeTransition.setFromValue(0.0);
                fadeTransition.setToValue(1.0);

                fadeTransition.play();
            } else {
                lblerror.setText("Неправильный логин или пароль");
            }
        });

    }

}
