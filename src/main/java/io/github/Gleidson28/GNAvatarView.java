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

import javafx.beans.DefaultProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * AvatarView is a simple component used to view one image with a custom avatar.
 * Create a shape with image for avatar view.
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/12/2018
 *
 */
@DefaultProperty("image")
@SuppressWarnings("unused")
public class GNAvatarView extends Region {

    private final Circle      circle    = new Circle();
    private final Rectangle   rectangle = new Rectangle();

    private final ObjectProperty<Image>         image = new SimpleObjectProperty<>(this, "image");
    private final ObjectProperty<ImagePattern>  imagePattern = new SimpleObjectProperty<>(this, "imagePattern");
    private final ObjectProperty<AvatarType>    type = new SimpleObjectProperty<>(this, "avatarType");
    private final ObjectProperty<Paint>         stroke = new SimpleObjectProperty<>(this, "stroke");
    private final DoubleProperty                strokeWidth = new SimpleDoubleProperty(this, "strokeWidth");

    private String nameImage;
    private String path;

    private final ChangeListener<Bounds> circleListener = (observable, oldValue, newValue) -> {
        double width = newValue.getWidth() / 2;
        double height = newValue.getHeight() / 2;
        double size = Math.min(width, height);

        circle.setRadius(size - (getStrokeWidth() / 2));
        circle.setCenterY(height);
        circle.setCenterX(width);
    };

    private final ChangeListener<Bounds> rectListener = (observable, oldValue, newValue) -> {
        double width = newValue.getWidth() ;
        double height = newValue.getHeight();

        // Configure aspect ratio
        if(height > width){
            rectangle.setWidth(width - strokeWidth.get());
            rectangle.setHeight(width - strokeWidth.get());
        } else {
            rectangle.setWidth(height - strokeWidth.get());
            rectangle.setHeight(height - strokeWidth.get());
        }

        // Centralize rectangle
        rectangle.setX( (newValue.getWidth() / 2) - (rectangle.getWidth() / 2) );
        rectangle.setY( (newValue.getHeight() / 2) - (rectangle.getHeight() / 2));
    };


    public GNAvatarView() {
        this(null);
    }

    public GNAvatarView(String path){
        this(path, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    }

    public GNAvatarView(double width, double height){
        this(null, width, height);
    }

    public GNAvatarView(String path, double width, double height) {

        this.getStyleClass().add("gn-avatar-view");

        registerListeners();
        registerBinds();

        setType(AvatarType.RECT);
        setPrefSize(width, height);

        setStrokeWidth(2);
        setStroke(Color.WHITE);

//        this.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        if(path != null) this.imagePattern.set(new ImagePattern(new Image(path)));
    }

    private void registerBinds(){
        circle.fillProperty().bind(imagePattern);
        circle.strokeProperty().bind(stroke);
        circle.strokeWidthProperty().bind(strokeWidth);

        rectangle.fillProperty().bind(imagePattern);
        rectangle.strokeProperty().bind(stroke);
        double prefHeight = 200D;
        rectangle.setHeight(prefHeight);
        double prefWidth = 200D;
        rectangle.setWidth(prefWidth);
        rectangle.strokeWidthProperty().bind(strokeWidth);
        rectangle.setArcHeight(20D);
        rectangle.setArcWidth(20D);
    }

    private void registerListeners(){
        image.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue != oldValue) {
                    imagePattern.set(new ImagePattern(newValue));
                }
            }
        });

        type.addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case CIRCLE:
                    getChildren().clear();
                    getChildren().add(circle);
                    layoutBoundsProperty().removeListener(rectListener);
                    layoutBoundsProperty().addListener(circleListener);
                    break;
                case RECT:
                    getChildren().clear();
                    getChildren().add(rectangle);
                    layoutBoundsProperty().removeListener(circleListener);
                    layoutBoundsProperty().addListener(rectListener);
                    break;
            }
        });
    }

    public AvatarType getType() {
        return type.get();
    }

    public ObjectProperty<AvatarType> typeProperty() {
        return type;
    }

    public void setType(AvatarType type) {
        this.type.set(type);
    }

    public Paint getStroke() {
        return stroke.get();
    }

    public ObjectProperty<Paint> strokeProperty() {
        return stroke;
    }

    public void setStroke(Paint stroke) {
        this.stroke.set(stroke);
    }

    public double getStrokeWidth() {
        return strokeWidth.get();
    }

    public DoubleProperty strokeWidthProperty() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth.set(strokeWidth);
    }

    public Image getImage() {
        return image.get();
    }

    public ObjectProperty<Image> imageProperty() {
        return image;
    }

    public void setImage(Image image) {
        this.image.set(image);
    }

    public ImagePattern getImagePattern() {
        return imagePattern.get();
    }

    public ObjectProperty<ImagePattern> imagePatternProperty() {
        return imagePattern;
    }

    public void setImagePattern(ImagePattern imagePattern) {
        this.imagePattern.set(imagePattern);
    }
}
