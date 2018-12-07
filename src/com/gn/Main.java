package com.gn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Image image = new Image(getClass().getResource("smile.jpg").toExternalForm());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(new GNAvatarView(image), 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
