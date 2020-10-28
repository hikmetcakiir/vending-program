package com.hikmetcakir.vendingprogram;

import com.hikmetcakir.vendingprogram.domain.ProductOperationService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui/MainWindow.fxml"));
        primaryStage.setTitle("Otomat Makinesi");
        primaryStage.setScene(new Scene(root, 1084, 574));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception{
        System.out.println("--APPLICATION STARTED--");
        launch(args);
    }
}
