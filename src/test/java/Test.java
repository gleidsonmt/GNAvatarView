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

import io.github.Gleidson28.AvatarType;
import io.github.Gleidson28.GNAvatarView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  30/06/2020
 */
public class Test extends Application {


    @Override
    public void start(Stage stage) {
//        createDefaultAvatarWithPath(stage);
//        createDefaultAvatarWithImage(stage);
//        createRectAvatar(stage);
        createCircleAvatar(stage);
    }

    private void createDefaultAvatarWithPath(Stage stage){
        stage.setTitle("Create Default Avatar With Path");

        GNAvatarView avatarView = new GNAvatarView("smile.jpg", 1000,300);

        stage.setScene(new Scene(avatarView));
        stage.show();
    }

    private void createDefaultAvatarWithImage(Stage stage){
        stage.setTitle("Create Default Avatar With Path");

        GNAvatarView avatarView = new GNAvatarView();
        Image image = new Image("smile.jpg");
        avatarView.setImage(image);
        avatarView.setPrefSize(600,660);
        stage.setScene(new Scene(avatarView));
        stage.show();
    }

    private void createRectAvatar(Stage stage){
        stage.setTitle("Create Rect Avatar");

        GNAvatarView avatarView = new GNAvatarView("smile.jpg", 1000,300);
        avatarView.setType(AvatarType.RECT);
        stage.setScene(new Scene(avatarView));
        stage.show();

    }

    private void createCircleAvatar(Stage stage){
        stage.setTitle("Create Rect Avatar");

        GNAvatarView avatarView = new GNAvatarView("smile.jpg", 1000,300);
        avatarView.setType(AvatarType.CIRCLE);
        stage.setScene(new Scene(avatarView));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
