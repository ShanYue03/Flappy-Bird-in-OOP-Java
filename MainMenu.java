package application;

import javafx.scene.Scene;

import javafx.scene.effect.DropShadow;
//import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu {
    private Stage primaryStage;
    private static Voice voice;

    public MainMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;
        if (voice == null) {
            voice = new Voice(); // Initialize the Voice instance once
        }
        FlappyBird.loadScores();
    }

    public void show() {
   	 if (!voice.isPlaying()) {
            voice.playSongloop();
        }
   	 
   	    Voice click = new Voice();
        StackPane mainMenu = new StackPane();

        ImageView background = new ImageView(new Image("file:/D:/OOP Java/FlappyBird/flappybirdbg.png"));
        background.setFitWidth(360);
        background.setFitHeight(640);

        Text title = new Text("Flappy Bird");
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/ARCADECLASSIC.TTF"),50);
        title.setFont(customFont);
        //title.setFont(Font.font("Pixelade", FontWeight.BOLD, 50));
        title.setFill(Color.BLACK);
        
        DropShadow glow = new DropShadow();
        glow.setColor(Color.WHITE);
        glow.setWidth(30);
        glow.setHeight(30);
        glow.setRadius(10);
        title.setEffect(glow);


        ImageView birdImage = new ImageView(new Image("file:/D:/OOP Java/FlappyBird/flappybird.png"));
        birdImage.setFitWidth(50);
        birdImage.setFitHeight(35);

        StyledButton startButton = new StyledButton("Start Game");
        startButton.setOnAction(e -> {
        	click.playSong(4);showNameInputScreen();});
        StyledButton leaderboardButton = new StyledButton("Leaderboard");
        leaderboardButton.setOnAction(e -> {
        	click.playSong(4);new HighestScore(primaryStage).show();});
        
        StyledButton themeButton = new StyledButton("Select Theme");
        themeButton.setOnAction(e -> {
            click.playSong(4);
            new ThemeSelection(primaryStage).show();
        });
        

        mainMenu.getChildren().addAll(background, title, birdImage, startButton, leaderboardButton, themeButton);
        StackPane.setAlignment(title, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(title, new javafx.geometry.Insets(150, 0, 0, 0));
        StackPane.setAlignment(birdImage, javafx.geometry.Pos.CENTER);
        StackPane.setAlignment(startButton, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setMargin(startButton, new javafx.geometry.Insets(0, 0, 200, 0));
        StackPane.setAlignment(leaderboardButton, javafx.geometry.Pos.CENTER);
        StackPane.setMargin(leaderboardButton, new javafx.geometry.Insets(290, 0, 0, 0));
        StackPane.setAlignment(themeButton, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setMargin(themeButton, new javafx.geometry.Insets(0, 0, 113, 0));

        Scene mainMenuScene = new Scene(mainMenu, 360, 640);
        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle("Flappy Bird");
        primaryStage.show();
    }

    private void showNameInputScreen() {
   	    Voice click = new Voice();
        StackPane nameInputLayout = new StackPane();

        ImageView background = new ImageView(new Image("file:/D:/OOP Java/FlappyBird/flappybirdbg.png"));
        background.setFitWidth(360);
        background.setFitHeight(640);

        Text instructions = new Text("Enter your name:");
        instructions.setFont(new Font("Arial", 22));

        javafx.scene.control.TextField nameInput = new javafx.scene.control.TextField();
        nameInput.setPromptText("Enter your name");

        StyledButton startGameButton = new StyledButton("Ready");
        startGameButton.setOnAction(e -> {click.playSong(4);voice.stopSong();startGame(nameInput.getText()); });
        
        StyledButton backButton = new StyledButton("Back to Main");
        backButton.setOnAction(e -> {click.playSong(4);show();});

        nameInputLayout.getChildren().addAll(background, instructions, nameInput, startGameButton, backButton);
        StackPane.setAlignment(instructions, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(instructions, new javafx.geometry.Insets(250, 0, 0, 0));
        StackPane.setAlignment(nameInput, javafx.geometry.Pos.CENTER);
        StackPane.setAlignment(startGameButton, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setMargin(startGameButton, new javafx.geometry.Insets(0, 0, 240, 0));
        StackPane.setAlignment(backButton, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setMargin(backButton, new javafx.geometry.Insets(0, 0, 80, 0));

        Scene nameInputScene = new Scene(nameInputLayout, 360, 640);
        primaryStage.setScene(nameInputScene);
    }

    private void startGame(String playerName) {
        FlappyBird game = new FlappyBird(playerName, primaryStage);
        Scene gameScene = new Scene(game, 360, 640);
        primaryStage.setScene(gameScene);
        game.startGame();
    }
}
