//package application;
//
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.StackPane;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//public class ThemeSelection {
//    private Stage primaryStage;
//    private String selectedTheme;
//    private static final String DAY_THEME = "file:/D:/OOP Java/FlappyBird/flappybirdbg.png";
//    private static final String NIGHT_THEME = "file:/D:/OOP Java/FlappyBird/flappybirdbgdark2.png";
//
//    public ThemeSelection(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//        this.selectedTheme = DAY_THEME; // Default theme
//    }
//
//    public void show() {
//    	Voice click = new Voice();
//        StackPane themeSelectionLayout = new StackPane();
//
//        ImageView background = new ImageView(new Image(selectedTheme));
//        background.setFitWidth(360);
//        background.setFitHeight(640);
//
//        Text instructions = new Text("Select Theme:");
//        instructions.setFont(new Font("Arial", 22));
//
//        StyledButton dayButton = new StyledButton("Day Mode");
//        dayButton.setOnAction(e -> {
//        	click.playSong(4);selectTheme(DAY_THEME);});
//
//        StyledButton nightButton = new StyledButton("Night Mode");
//        nightButton.setOnAction(e -> {
//        	click.playSong(4);selectTheme(NIGHT_THEME);});
//
//        StyledButton backButton = new StyledButton("Back to Main");
//        backButton.setOnAction(e -> {
//        	click.playSong(4);new MainMenu(primaryStage).show();});
//
//        themeSelectionLayout.getChildren().addAll(background, instructions, dayButton, nightButton, backButton);
//        StackPane.setAlignment(instructions, javafx.geometry.Pos.TOP_CENTER);
//        StackPane.setMargin(instructions, new javafx.geometry.Insets(150, 0, 0, 0));
//        StackPane.setAlignment(dayButton, javafx.geometry.Pos.CENTER_LEFT);
//        StackPane.setMargin(dayButton, new javafx.geometry.Insets(0, 0, 0, 70));
//        StackPane.setAlignment(nightButton, javafx.geometry.Pos.CENTER_RIGHT);
//        StackPane.setMargin(nightButton, new javafx.geometry.Insets(0, 70, 0, 0));
//        StackPane.setAlignment(backButton, javafx.geometry.Pos.BOTTOM_CENTER);
//        StackPane.setMargin(backButton, new javafx.geometry.Insets(0, 0, 100, 0));
//
//        Scene themeSelectionScene = new Scene(themeSelectionLayout, 360, 640);
//        primaryStage.setScene(themeSelectionScene);
//    }
//
//    private void selectTheme(String theme) {
//        selectedTheme = theme;
//        GameView.setTheme(selectedTheme);
//    }
//}





package application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
//import javafx.scene.layout.Border;
//import javafx.scene.layout.BorderStroke;
//import javafx.scene.layout.BorderStrokeStyle;
//import javafx.scene.layout.BorderWidths;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.BorderPane;

public class ThemeSelection {
    private Stage primaryStage;
    private String selectedTheme;
    private static final String DAY_THEME = "file:/D:/OOP Java/FlappyBird/flappybirdbg.png";
    private static final String NIGHT_THEME = "file:/D:/OOP Java/FlappyBird/flappybirdbgdark2.png";

    public ThemeSelection(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.selectedTheme = DAY_THEME; // Default theme
    }

    public void show() {
    	Voice click = new Voice();
        StackPane themeSelectionLayout = new StackPane();

        ImageView background = new ImageView(new Image(selectedTheme));
        background.setFitWidth(360);
        background.setFitHeight(640);

        Text instructions = new Text("Select Theme:");
        instructions.setFont(new Font("Arial", 22));

        // Day Mode
        ImageView dayImage = new ImageView(new Image(DAY_THEME));
        dayImage.setFitWidth(120);
        dayImage.setFitHeight(200);
        dayImage.setEffect(new DropShadow(10, Color.BLACK));
        dayImage.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        StyledButton dayButton = new StyledButton("Day Mode");
        dayButton.setOnAction(e -> {
        	click.playSong(4);selectTheme(DAY_THEME);});

        // Night Mode
        ImageView nightImage = new ImageView(new Image(NIGHT_THEME));
        nightImage.setFitWidth(120);
        nightImage.setFitHeight(200);
        nightImage.setEffect(new DropShadow(10, Color.BLACK));
        nightImage.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        StyledButton nightButton = new StyledButton("Night Mode");
        nightButton.setOnAction(e -> {
        	click.playSong(4);selectTheme(NIGHT_THEME);});;

        StyledButton backButton = new StyledButton("Back to Main");
        backButton.setOnAction(e -> {
        	click.playSong(4);new MainMenu(primaryStage).show();});

        VBox dayBox = new VBox(dayImage, dayButton);
        dayBox.setSpacing(10);
        dayBox.setAlignment(javafx.geometry.Pos.CENTER);

        VBox nightBox = new VBox(nightImage, nightButton);
        nightBox.setSpacing(10);
        nightBox.setAlignment(javafx.geometry.Pos.CENTER);

        HBox themeBox = new HBox(dayBox, nightBox);
        themeBox.setSpacing(30);
        themeBox.setAlignment(javafx.geometry.Pos.CENTER);
        themeBox.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));

        VBox mainLayout = new VBox(instructions, themeBox, backButton);
        mainLayout.setSpacing(30);
        mainLayout.setAlignment(javafx.geometry.Pos.CENTER);

        themeSelectionLayout.getChildren().addAll(background, mainLayout);

        Scene themeSelectionScene = new Scene(themeSelectionLayout, 360, 640);
        primaryStage.setScene(themeSelectionScene);
    }

    private void selectTheme(String theme) {
        selectedTheme = theme;
        GameView.setTheme(selectedTheme);
    }
}
