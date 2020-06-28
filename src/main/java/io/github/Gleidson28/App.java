/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.Gleidson28;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Image image = new Image(getClass().getResource("smile.jpg").toExternalForm());
        primaryStage.setTitle("Hello World");

        GNAvatarView avatarView = new GNAvatarView("smile.jpg", 1000,300);
//        GNAvatarView avatarView = new GNAvatarView();
//        avatarView.setImage(new Image(getClass().getResource("/com/gn/SimpleLightGreen.jpg").toExternalForm()));

        primaryStage.setScene(new Scene(avatarView));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
