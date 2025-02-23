package application;

import javafx.scene.Scene;

//import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class HighestScore {
    private Stage primaryStage;

    public HighestScore(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
    	Voice click = new Voice();
        StackPane highestScoreLayout = new StackPane();

        ImageView background = new ImageView(new Image("file:/D:/OOP Java/FlappyBird/flappybirdbg.png"));
        background.setFitWidth(360);
        background.setFitHeight(640);

        Text highestScoreText = new Text("Highest Scores");
        //highestScoreText.setFont(Font.font("Pixelade", FontWeight.BOLD, 40));
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/ARCADECLASSIC.TTF"),40);
        highestScoreText.setFont(customFont);
        highestScoreText.setFill(Color.YELLOW);

        Map<String, Integer> userScores = FlappyBird.getUserScores();
        String scores = userScores.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> entry.getKey() + ":  " + entry.getValue())
                .collect(Collectors.joining("\n"));
        
        Text scoresText = new Text(scores);
        scoresText.setFont(new Font("Arial", 16));

        //Button mainMenuButton = new Button("Main Menu");
        StyledButton mainMenuButton = new StyledButton("Main Menu");
        mainMenuButton.setOnAction(e -> {click.playSong(4);new MainMenu(primaryStage).show();});

        highestScoreLayout.getChildren().addAll(background, highestScoreText, scoresText, mainMenuButton);
        StackPane.setAlignment(highestScoreText, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(highestScoreText, new javafx.geometry.Insets(50, 0, 0, 0));
        StackPane.setAlignment(scoresText, javafx.geometry.Pos.CENTER);
        StackPane.setAlignment(mainMenuButton, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setMargin(mainMenuButton, new javafx.geometry.Insets(0, 0, 50, 0));

        Scene highestScoreScene = new Scene(highestScoreLayout, 360, 640);
        primaryStage.setScene(highestScoreScene);
    }
}
