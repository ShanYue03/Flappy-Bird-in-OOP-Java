# Flappy-Bird-in-OOP-Java
-------------------------------------------------------------------------------------------------------------------------
## 🕹️ Flappy Bird Game
A JavaFX-based Flappy Bird clone featuring object-oriented design, dynamic difficulty, sound effects, theme customization, and persistent leaderboards. Built to demonstrate advanced Java programming and OOP concepts.

## 🚀 Key Features
1. Player Customization: Enter your name and track high scores with a serialized leaderboard (scores.dat).

2. Dynamic Difficulty: Speed and gravity adjust as score increases (adjustDifficulty()).

3. Audio Integration:

 - Sound effects for jumps, collisions, and UI interactions (Voice class with MediaPlayer).

 - Background music loop during gameplay.

 - Theme Support: Switch between day/night modes (ThemeSelection class).

4. Responsive UI:

 - Stylish buttons with hover/click effects (StyledButton).

 - Animated countdown and game-over screens.

## 🧩 Class Structure & OOP Principles
1. Encapsulation

- FlappyBird encapsulates game logic (bird physics, pipe generation, collision detection).

- Scoreboard data is managed via serialization (saveScores(), loadScores()).

2. Inheritance

- JavaFX components extended (e.g., GameView inherits Pane, FlappyBird extends StackPane).

3. Polymorphism

- Overloaded playSong() in Voice class for different sound effects.

- Dynamic UI updates via method overrides (e.g., draw() in GameView).

4. Abstraction

- Separated game logic (FlappyBird) from UI rendering (GameView).

- Abstracted pipe behavior into Pipe nested class.

## 🛠️ Technical Implementation
Game Loop:

Uses JavaFX AnimationTimer for smooth rendering.

Physics-based bird movement (velocityY, gravity variables).

Collision System:

Pixel-perfect collision detection between bird/pipes (collision() method).

File I/O:

Scores persisted via ObjectOutputStream/ObjectInputStream.

Dependency Management:

Image/sound files loaded from absolute paths (customize with relative paths for portability).

🔌 Dependencies & Imports
JavaFX: Used for UI rendering (Scene, Canvas, AnimationTimer).

Media Handling: javafx.scene.media for audio playback.

File I/O: java.io.Serializable for leaderboard persistence.

## 🎯 Skills Demonstrated
1. Advanced OOP: Clean separation of concerns between game logic, UI, and audio.

2. JavaFX Mastery: Custom animations, event handling, and responsive layouts.

3. Resource Management:

- Image loading from disk (new Image("file:path.png")).

- Audio integration with threading via PauseTransition.

4. Code Quality:

- Constants (WIDTH, HEIGHT) for maintainability.

- Modular collision detection and physics systems.

## 📦 How to Run
Requirements:

Java 17+ with JavaFX SDK configured.

Run:

bash
Copy
javac --module-path $PATH_TO_JAVAFX --add-modules javafx.controls,javafx.media application/*.java  
java --module-path $PATH_TO_JAVAFX --add-modules javafx.controls,javafx.media application.PlayFlappyBirdApp  
