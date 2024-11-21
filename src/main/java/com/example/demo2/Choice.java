package com.example.demo2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Choice {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button kn1;

    @FXML
    private Button kn2;

    @FXML
    private Button kn3;

    @FXML
    void initialize() {
        kn1.setOnAction(actionEvent -> {
            kn1.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main1.fxml"));

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


            // Запускаем анимацию перед отображением окна
            fadeTransition.play();
        });

        kn2.setOnAction(actionEvent -> {
            kn2.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main2.fxml"));


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

            // Запускаем анимацию перед отображением окна
            fadeTransition.play();
        });

        kn3.setOnAction(actionEvent -> {
            kn3.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main3.fxml"));


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

            // Запускаем анимацию перед отображением окна
            fadeTransition.play();
        });
    }

}
