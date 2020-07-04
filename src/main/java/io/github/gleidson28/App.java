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

package io.github.gleidson28;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void init() {
        System.out.println("Hellow word");
    }

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("View Samples");

        HBox content = new HBox();
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(10D));
        content.setSpacing(10D);

        GNAvatarView rectAvatar = new GNAvatarView("smile.jpg", 200,200, AvatarType.RECT);
        rectAvatar.setStroke(Color.BLACK);

        GNAvatarView circleAvatar = new GNAvatarView("smile.jpg", 200,200, AvatarType.CIRCLE);
        circleAvatar.setStroke(Color.BLACK);

        GNAvatarView circleDash = new GNAvatarView("smile.jpg", 200,200, AvatarType.CIRCLE);
        circleDash.setStroke(Color.BLACK);
        circleDash.setStrokeDashArray(10D);
        circleDash.setStrokeDashOffset(6D);

        content.getChildren().addAll(rectAvatar, circleAvatar, circleDash);

        stage.setScene(new Scene(content));
        stage.show();
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
