//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.snake;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
    static int score;
    static Text record;
    static Circle circle;
    static boolean snakeDied = false;
    static Fruit fruit = new Fruit();
    static Snake snake = new Snake();
    static Timeline animation = null;
    static final Group stackPane = new Group();
    static Rectangle rec = new Rectangle(400.0, 300.0);
    static Text gameOver = new Text("GAME OVER!");
    static Button restart = new Button("Restart");
    static Button closeWindow = new Button("Close");
    static Dir direction;

    static {
        direction = Game.Dir.left;
    }

    public Game() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("SNAKE GAME");
        stackPane.setStyle("CASPIAN");
        record = new Text();
        record.setWrappingWidth(500.0);
        record.setY(50.0);
        record.setTextAlignment(TextAlignment.CENTER);
        record.setFill(Color.BLACK);
        record.setFont(Font.font(25.0));
        stackPane.getChildren().addAll(snake.getSnake());
        stackPane.getChildren().add(fruit.getFruit());
        stackPane.getChildren().add(record);
        Scene scene = new Scene(stackPane, 500.0, 500.0);
        setAnimation();
        scene.addEventFilter(KeyEvent.KEY_RELEASED, (e) -> {
            if (!snakeDied) {
                if (e.getCode() == KeyCode.DOWN) {
                    direction = Game.Dir.down;
                    setAnimation();
                } else if (e.getCode() == KeyCode.UP) {
                    direction = Game.Dir.up;
                    setAnimation();
                } else if (e.getCode() == KeyCode.LEFT) {
                    direction = Game.Dir.left;
                    setAnimation();
                } else if (e.getCode() == KeyCode.RIGHT) {
                    direction = Game.Dir.right;
                    setAnimation();
                }
            }

        });
        stage.setScene(scene);
        stage.show();
    }

    public static void restartGame() {
        stackPane.getChildren().removeAll(new Node[]{rec, gameOver, restart, closeWindow});
        stackPane.getChildren().removeAll(snake.getSnake());
        stackPane.getChildren().remove(record);
        score = 0;
        record.setText("Score : " + score);
        snakeDied = false;
        snake = new Snake();
        stackPane.getChildren().add(record);
        stackPane.getChildren().addAll(snake.getSnake());
        animation.play();
    }

    public static void gameOverScreen() {
        rec.setFill(Color.BLACK);
        rec.setX(50.0);
        rec.setY(100.0);
        rec.setOpacity(0.4);
        rec.setEffect(new Glow(0.5));
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(3.0);
        shadow.setColor(Color.color(0.4000000059604645, 0.4000000059604645, 0.4000000059604645));
        gameOver.setWrappingWidth(500.0);
        gameOver.setEffect(shadow);
        gameOver.setCache(true);
        gameOver.setY(150.0);
        gameOver.setTextAlignment(TextAlignment.CENTER);
        gameOver.setFill(Color.RED);
        gameOver.setFont(Font.font((String)null, FontWeight.BOLD, 32.0));
        rec.requestFocus();
        closeWindow.setMinWidth(200.0);
        closeWindow.setMinHeight(50.0);
        closeWindow.setWrapText(true);
        closeWindow.setLayoutX(150.0);
        closeWindow.setLayoutY(275.0);
        closeWindow.setOnAction((ActionEvent) -> {
            Platform.exit();
        });
        restart.setMinWidth(200.0);
        restart.setMinHeight(50.0);
        restart.setWrapText(true);
        restart.setLayoutX(150.0);
        restart.setLayoutY(200.0);
        restart.setOnAction((ActionEvent) -> {
            restartGame();
        });
        stackPane.getChildren().addAll(new Node[]{rec, gameOver, restart, closeWindow});
    }

    public static void setAnimation() {
        if (animation != null) {
            animation.pause();
        }

        animation = new Timeline(new KeyFrame[]{new KeyFrame(Duration.millis(100.0), (ev) -> {
            snake.moveSnake(direction, 10);
        }, new KeyValue[0])});
        animation.setCycleCount(-1);
        animation.play();
    }

    public static boolean isFruitEaten(Snake snake, Fruit fruit) {
        return fruit.getPos()[0] == snake.getSnakeHead().getCenterX() && fruit.getPos()[1] == snake.getSnakeHead().getCenterY();
    }

    public static void spawnFruit() {
        fruit.newFruit();
        Circle circle = new Circle(((Circle)snake.getSnake().get(snake.getSnake().size() - 1)).getCenterX(), ((Circle)snake.getSnake().get(snake.getSnake().size() - 1)).getCenterY() - 15.0, 5.0);
        circle.setFill(Color.LAWNGREEN);
        snake.addToSnake(circle);
        stackPane.getChildren().add(circle);
    }

    static enum Dir {
        right,
        left,
        down,
        up;

        private Dir() {
        }
    }
}
