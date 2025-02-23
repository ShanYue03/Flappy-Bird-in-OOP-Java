package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameView extends Pane {
    private static final int WIDTH = 360;
    private static final int HEIGHT = 640;
    private static String selectedTheme = "file:/D:/OOP Java/FlappyBird/flappybirdbg.png";

    private Canvas canvas;
    private GraphicsContext gc;
    private Image backgroundImg;
    private Image birdImg;
    private Image topPipeImg;
    private Image bottomPipeImg;
    private FlappyBird game;

    //Constructor
    public GameView(FlappyBird game) {
        this.game = game;
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);
        loadImages();
        game.setImages(birdImg, topPipeImg, bottomPipeImg);
        setFocusTraversable(true);
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                game.jump();
            }
        });
    }

    private void loadImages() {
        backgroundImg = new Image(selectedTheme);
        birdImg = new Image("file:/D:/OOP Java/FlappyBird/flappybird.png");
        topPipeImg = new Image("file:/D:/OOP Java/FlappyBird/toppipe.png");
        bottomPipeImg = new Image("file:/D:/OOP Java/FlappyBird/bottompipe.png");
    }

    void draw() {
        gc.drawImage(backgroundImg, 0, 0, WIDTH, HEIGHT);
        game.draw(gc);
    }

    void drawCountdown(long l) {
        gc.drawImage(backgroundImg, 0, 0, WIDTH, HEIGHT);
        gc.setFont(Font.font("System", FontWeight.BOLD, 20));
        gc.setFill(Color.YELLOW);
        game.drawCountdown(gc, l);
    }
    
    public static void setTheme(String theme) {
        selectedTheme = theme;
    }
}
