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
package com.gn;

import javafx.beans.DefaultProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/12/2018
 */
@DefaultProperty("image")
public class GNAvatarView extends Region {

    private double prefWidth  = 200D;
    private double prefHeight = 200D;

    private Circle      circle    = new Circle();
    private Rectangle   rectangle = new Rectangle();

    private ObjectProperty<Image> image = new SimpleObjectProperty<>(this, "image");
    private ObjectProperty<ImagePattern> imagePattern = new SimpleObjectProperty<>(this, "imagePattern");
    private ObjectProperty<AvatarType> type = new SimpleObjectProperty<>(this, "avatarType");
    private ObjectProperty<Paint> stroke = new SimpleObjectProperty<>(this, "stroke");
    private DoubleProperty strokeWidth = new SimpleDoubleProperty(this, "strokeWidth");

    private ChangeListener<Bounds> circleListener = (observable, oldValue, newValue) -> {
        double width = newValue.getWidth() / 2;
        double height = newValue.getHeight() / 2;
        double size = width < height ? width : height;

        circle.setRadius(size - (getStrokeWidth() / 2));
        circle.setCenterY(height);
        circle.setCenterX(width);
    };

    private ChangeListener<Bounds> rectListener = (observable, oldValue, newValue) -> {
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

    public GNAvatarView(Image image){

        registerListeners();
        registerBinds();

        setType(AvatarType.RECT);
        setPrefSize(prefWidth, prefHeight);

        setStrokeWidth(2);
        setStroke(Color.WHITE);

        if(image != null) this.imagePattern.set(new ImagePattern(image));
    }

    private void registerBinds(){
        circle.fillProperty().bind(imagePattern);
        circle.strokeProperty().bind(stroke);
        circle.strokeWidthProperty().bind(strokeWidth);
        rectangle.fillProperty().bind(imagePattern);
        rectangle.strokeProperty().bind(stroke);
        rectangle.setHeight(prefHeight);
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
