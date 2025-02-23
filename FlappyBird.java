package application;

import javafx.animation.AnimationTimer;



import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
//import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//declares 'FlappyBird' class, which extents a JavaFX layout container 'StackPane'
public class FlappyBird extends StackPane {


//declares 'Constants'
    private static final int WIDTH = 360;
    private static final int HEIGHT = 640;
    private static final int PIPE_WIDTH = 64;
    private static final int PIPE_HEIGHT = 512;
    private static final int PIPE_GAP = 150;
    private static final int BIRD_WIDTH = 34;
    private static final int BIRD_HEIGHT = 24;
    private static final String SCORE_FILE = "scores.dat"; //dat file to store user name and scores

    private Image birdImg;
    private Image topPipeImg;
    private Image bottomPipeImg;

//track bird's position and movement
    private double birdX;
    private double birdY;
    private double velocityY;
    private double gravity;

    private ArrayList<Pipe> pipes;
    private Random random;
    private double score;
    private boolean gameOver;

    private GameView gameView;
    private String playerName;
    private Stage primaryStage;
    private static Map<String, Integer> userScores = new HashMap<>();
    private double basePipeSpeed = 1.1;  // Base pipe speed
    private double baseJumpVelocity = -7.0;  // Base jump velocity


//Constructor

    //Initializes bird position, velocity and gravity
    public FlappyBird(String playerName, Stage primaryStage) {
        this.playerName = playerName;
        this.primaryStage = primaryStage;
        birdX = WIDTH / 8.0;
        birdY = HEIGHT / 2.0;
        velocityY = 0;
        gravity = 0.1;

        //Initializes pipes randomly, initial score
        pipes = new ArrayList<>();
        random = new Random();
        score = 0;
        gameOver = false;

        //Call class GameView and inherit the methods
        gameView = new GameView(this);
        getChildren().add(gameView);
        placePipes();
        loadScores();
    }

    //method to set images for birds and pipes
    public void setImages(Image birdImg, Image topPipeImg, Image bottomPipeImg) {
        this.birdImg = birdImg;
        this.topPipeImg = topPipeImg;
        this.bottomPipeImg = bottomPipeImg;
    }

    //method to start game
    public void startGame() {

        //1. Create a new AnimationTimer instance
        new AnimationTimer() {

            //2. Define a private field to store the start time of the game
            private long startTime = System.currentTimeMillis();

             //3. Define a private field to check countdown is finish or not
            private boolean countdownFinished = false;

            @Override
            public void handle(long now) {

                //4. get current time
                long currentTime = System.currentTimeMillis();

                //5. check if 3 seconds have passed (3000 Ms)
                if (currentTime - startTime >= 3000) {

                    //6. if countdown not finish, continue and moves bird up slightly
                    if (!countdownFinished) {
                        countdownFinished = true;
                        velocityY = -2;  // Give an initial slight upward movement
                    }
                    update();

                    //7. Draw the current game state on the screen(call GameView)
                    gameView.draw();
                    if (isGameOver()) {
                        stop();
                        saveScore();
                        showGameOverOptions();
                    }
                } else {
                    gameView.drawCountdown(3 - (currentTime - startTime) / 1000);
                }
            }
        }.start(); //Start the animation timer
    }
    

    //Returns gameover
    public boolean isGameOver() {
        return gameOver;
    }


    //method to update game status, move bird, check collision and update score
    private void update() {

        //1. Check if the game is over
        if (gameOver) return;
        adjustDifficulty();

        //2. Apply gravity to the bird vertical velocity
        velocityY += gravity;

        //3. Move bird vertically
        birdY += velocityY;

        //4. Ensure bird does not go above the top screen
        birdY = Math.max(birdY, 0);


        //5. Iterate each pipe
        for (Pipe pipe : pipes) {

            //6. Move pipe to the left(pipe speed)
            pipe.x -= basePipeSpeed;

            //7. Check if the bird has passed and update score
            if (!pipe.passed && birdX > pipe.x + PIPE_WIDTH) {
                score += 0.5;
                pipe.passed = true;
            }

            //8. Check for collision of bird and pipe
            if (collision(pipe)) {
                gameOver = true;
            }
        }
        
        //9. Check if the bird hit the ground
        if (birdY > HEIGHT - BIRD_HEIGHT) {
            gameOver = true;
        }

        //10. Remove pipes after went off the screen
        pipes.removeIf(pipe -> pipe.x < -PIPE_WIDTH);

        //11. Add new pipes
        if (pipes.size() < 2) { // Ensure only one pair of pipes is added
            placePipes();
        }
    }

    //Method to place pipes at a random height
    private void placePipes() {
        int randomY = random.nextInt(HEIGHT / 2) + PIPE_GAP;
        int openingSpace = PIPE_GAP;

        Pipe topPipe = new Pipe(WIDTH, randomY - openingSpace - PIPE_HEIGHT, topPipeImg);
        Pipe bottomPipe = new Pipe(WIDTH, randomY, bottomPipeImg);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    
    //Draw the bird, pipes, score and game over texts
    public void draw(GraphicsContext gc) {
        gc.drawImage(birdImg, birdX, birdY, BIRD_WIDTH, BIRD_HEIGHT);

        for (Pipe pipe : pipes) {
            gc.drawImage(pipe.img, pipe.x, pipe.y, PIPE_WIDTH, PIPE_HEIGHT);
        }

        gc.setFont(Font.font("System", FontWeight.BOLD, 20));
        gc.setFill(Color.YELLOW);
        gc.fillText("Score: " + (int) score, 10, 35);

        if (gameOver) {
        	Voice voice = new Voice();
        	voice.playSong(2);
            gc.setTextAlign(TextAlignment.CENTER); // Center align the text
            gc.setFill(Color.RED);
            Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/ARCADECLASSIC.TTF"),45);
            gc.setFont(customFont); 
            //gc.setFont(Font.font("Pixelade", FontWeight.BOLD, 45)); 
            gc.fillText("Game Over", WIDTH / 2.0, HEIGHT / 2.0);
        }
    }

    //Draw the countdown timer and bird before game starts
    public void drawCountdown(GraphicsContext gc, long l) {
        gc.drawImage(birdImg, birdX, birdY, BIRD_WIDTH, BIRD_HEIGHT);
        gc.fillText("Starting in: " + l, WIDTH / 2.0 - 50, HEIGHT / 2.0);
    }

    //Makes the bird jump by setting the velocity upwards
    public void jump() {
        if (gameOver) {
            restart();
        } else {
        	Voice voice = new Voice();
        	voice.playSong(0);
            velocityY = -3.0; //(Default = -5, change to -3 for smooth)
        }
    }

    //Resets the game
    private void restart() {
        this.getChildren().clear();
        FlappyBird newGame = new FlappyBird(playerName, primaryStage);
        Scene newGameScene = new Scene(newGame, WIDTH, HEIGHT);
        primaryStage.setScene(newGameScene);
        basePipeSpeed = 1.1;  // Reset pipe speed
        baseJumpVelocity = -7.0;  // Reset jump velocity
        gravity = 0.1;  // Reset gravity
        newGame.startGame();
    }

    //Display options to choose after game over
    private void showGameOverOptions() {
        StyledButton restartButton = new StyledButton("Restart");
        StyledButton mainMenuButton = new StyledButton("Main Menu");
        StyledButton leaderboardButton = new StyledButton("Leaderboard");

        restartButton.setOnAction(e -> restart());
        mainMenuButton.setOnAction(e -> new MainMenu(primaryStage).show());
        leaderboardButton.setOnAction(e -> new HighestScore(primaryStage).show());

        this.getChildren().addAll(restartButton, mainMenuButton, leaderboardButton);
        StackPane.setAlignment(restartButton, javafx.geometry.Pos.CENTER);
        StackPane.setMargin(restartButton, new javafx.geometry.Insets(90, 0, 0, 0));
        StackPane.setAlignment(mainMenuButton, javafx.geometry.Pos.CENTER);
        StackPane.setMargin(mainMenuButton, new javafx.geometry.Insets(190, 0, 0, 0));
        StackPane.setAlignment(leaderboardButton, javafx.geometry.Pos.CENTER);
        StackPane.setMargin(leaderboardButton, new javafx.geometry.Insets(290, 0, 0, 0));
    }

    //Check if the bird collided a pipe
    private boolean collision(Pipe pipe) {
        return birdX < pipe.x + PIPE_WIDTH && 
               birdX + BIRD_WIDTH > pipe.x && 
               birdY < pipe.y + PIPE_HEIGHT && 
               birdY + BIRD_HEIGHT > pipe.y;
    }

    
    //save player scores
    private void saveScore() {
        int currentScore = (int) score;
        
        //check if player exists in scores
        //userScores is a map, playerName is key
        if (userScores.containsKey(playerName)) {
            int highestScore = userScores.get(playerName);
            if (currentScore > highestScore) {
                //update the scores to the highest
                userScores.put(playerName, currentScore);
            }
        } else { //add a new entry for new user
            userScores.put(playerName, currentScore);
        }
        saveScores(); //calls saveScores() to update to file
    }

    //retrieve  user scores
    public static Map<String, Integer> getUserScores() {
        return userScores;
    }
    
    //save scores to file (SCORE_FILE)
    private void saveScores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCORE_FILE))) {
            oos.writeObject(userScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	public static void loadScores() {
        //creates input stream to read objects from a file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SCORE_FILE))) {
            
            //read object is cast to 'Map<String, Integer>' and assigned to userScores
            userScores = (Map<String, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Represents a pipe object with position, image
    private static class Pipe {
        double x;
        double y;
        Image img;
        boolean passed;

        Pipe(double x, double y, Image img) {
            this.x = x;
            this.y = y;
            this.img = img;
            this.passed = false;
        }
    }
    
    private void adjustDifficulty() {
        int difficultyMultiplier = (int) (score / 5);

        // Increase the speed of pipes
        basePipeSpeed = 1 + (0.2 * difficultyMultiplier);

        // Adjust the gravity and jump velocity for the bird
        baseJumpVelocity = -7 - (0.07 * difficultyMultiplier);
    }
}
