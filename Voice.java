package application;

import java.io.File;
import java.util.Random;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Voice extends Application {

    private MediaPlayer mediaPlayer;
    private Random random = new Random();
    private Label statusLabel = new Label();

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("MP3 Player");
        Button Button1 = new Button("Young&Beautiful");
        Button1.setOnAction(e -> playSong(0));
        Button Button2 = new Button("JumpSound");
        Button2.setOnAction(e -> playSong(1));
        Button Button3 = new Button("GameOver");
        Button3.setOnAction(e -> playSong(2));

        Label statusLabel = new Label("Status: Waiting");

        StackPane root = new StackPane();

        StackPane.setAlignment(Button1, Pos.TOP_CENTER);
        StackPane.setAlignment(Button2, Pos.CENTER);
        StackPane.setAlignment(Button3, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(statusLabel, Pos.BOTTOM_RIGHT);

        root.getChildren().addAll(Button1, Button2, Button3, statusLabel);
        

        Scene scene = new Scene(root, 300, 200);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE:
                    if (mediaPlayer != null) {
                        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                            mediaPlayer.pause();
                        } else {
                            mediaPlayer.play();
                        }
                    }
                    break;
                default:
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void playSong(int choice) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            // Use a switch to choose different songs based on a random number
            String filePath;
            switch (choice) {
                case 0:
                    filePath = "D:\\OOP Java\\GroupFlappyBird\\voice\\JumpSound2.mp3";
                    break;
                case 1:
                    filePath = "D:\\OOP Java\\GroupFlappyBird\\voice\\JumpSound.mp3";
                    break;
                case 2:
                    filePath = "D:\\OOP Java\\GroupFlappyBird\\voice\\GameOver.mp3";
                    break;
                case 3:
                    filePath = "D:\\OOP Java\\GroupFlappyBird\\voice\\startGame.mp3";
                    break;
                case 4:
                    filePath = "D:\\OOP Java\\GroupFlappyBird\\voice\\click.mp3";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }

            File file = new File(filePath);
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();

            int durationInSeconds = 1000; // Change this to the duration of your song in seconds
            int randomStopTime = random.nextInt(durationInSeconds); // Random stop time between 1 and durationInSeconds
            statusLabel.setText("Song is playing. Will stop at " + randomStopTime + " seconds.");

            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(randomStopTime));
            pauseTransition.setOnFinished(event -> stopSong());
            pauseTransition.play();

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error playing the song.");
        }
    }

    public void playSongloop() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            // Use a switch to choose different songs based on a random number
            String filePath;
                    filePath = "D:\\OOP Java\\GroupFlappyBird\\voice\\FarmMania.mp3";
                    //filePath = "D:\\OOP Java\\GroupFlappyBird\\voice\\startGame.mp3";

            File file = new File(filePath);
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();

            int durationInSeconds = 1000; // Change this to the duration of your song in seconds
            int randomStopTime = random.nextInt(durationInSeconds); // Random stop time between 1 and durationInSeconds
            statusLabel.setText("Song is playing. Will stop at " + randomStopTime + " seconds.");

            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(randomStopTime));
            pauseTransition.setOnFinished(event -> stopSong());
            pauseTransition.play();

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error playing the song.");
        }
    }
    public void stopSong() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            statusLabel.setText("Song stopped.");
        }
    }

    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

   
}