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

        GNAvatarView avatarView = new GNAvatarView("/com/gn/smile.jpg", 1000,300);
//        GNAvatarView avatarView = new GNAvatarView();
//        avatarView.setImage(new Image(getClass().getResource("/com/gn/SimpleLightGreen.jpg").toExternalForm()));

        primaryStage.setScene(new Scene(avatarView));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
