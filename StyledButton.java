package application;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class StyledButton extends Button {
    public StyledButton(String text) {
        super(text);
        initStyle();
    }

    private void initStyle() {
        // Apply inline styles to the button
        setStyle(
            "-fx-background-color: linear-gradient(#ffea00, #ffbf00); " +
            "-fx-background-radius: 15; " +
            "-fx-padding: 5 10 5 10; " +
            "-fx-font-size: 16px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: linear-gradient(#3366ff, #33ccff);"
        );

        // Add a drop shadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.4, 0.4));
        setEffect(dropShadow);

        // Add button hover effect
        setOnMouseEntered(event -> setEffect(null));
        setOnMouseExited(event -> setEffect(dropShadow));

        // Add button click effect
        setOnMousePressed(event -> setEffect(null));
        setOnMouseReleased(event -> setEffect(dropShadow));
    }
}

