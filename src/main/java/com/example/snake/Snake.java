//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.snake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Snake extends Circle {
    ArrayList<Circle> snake = new ArrayList();

    public Snake() {
        Circle circle1 = new Circle(250.0, 250.0, 5.0);
        Circle circle2 = new Circle(250.0, 235.0, 5.0);
        Circle circle3 = new Circle(250.0, 220.0, 5.0);
        circle1.setFill(Color.LAWNGREEN);
        circle2.setFill(Color.LAWNGREEN);
        circle3.setFill(Color.LAWNGREEN);
        this.snake.add(circle1);
        this.snake.add(circle2);
        this.snake.add(circle3);
    }

    public ArrayList<Circle> getSnake() {
        return this.snake;
    }

    public Circle getSnakeHead() {
        return (Circle)this.snake.get(0);
    }

    public void addToSnake(Circle circle) {
        this.snake.add(circle);
    }

    public void moveSnake(Game.Dir direction, int x) {
        if (Game.isFruitEaten(Game.snake, Game.fruit)) {
            ++Game.score;
            Game.record.setText("Score : " + Game.score);
            Game.fruit.newFruit();
            Game.circle = new Circle(((Circle)this.snake.get(this.snake.size() - 1)).getCenterX(), ((Circle)this.snake.get(this.snake.size() - 1)).getCenterY() - 15.0, 5.0);
            Game.circle.setFill(Color.LAWNGREEN);
            Game.snake.addToSnake(Game.circle);
            Game.stackPane.getChildren().add(Game.circle);
        }

        Iterator var4 = this.snake.subList(1, this.snake.size()).iterator();

        Circle snakeBody;
        while(var4.hasNext()) {
            snakeBody = (Circle)var4.next();
            if (this.getSnakeHead().getCenterX() == snakeBody.getCenterX() && this.getSnakeHead().getCenterY() == snakeBody.getCenterY()) {
                Game.animation.pause();
                Game.snakeDied = true;
                Game.gameOverScreen();
            }
        }

        Queue<Double> snakeBodyPos = new LinkedList();
        snakeBodyPos.add(this.getSnakeHead().getCenterY());
        snakeBodyPos.add(this.getSnakeHead().getCenterX());
        int snakeIndex = 1;
        label68:
        switch (direction) {
            case right:
                if (this.getSnakeHead().getCenterX() == 500.0) {
                    this.getSnakeHead().setCenterX(0.0);
                }

                this.getSnakeHead().setCenterX(this.getSnakeHead().getCenterX() + (double)x);

                while(true) {
                    if (snakeIndex > this.snake.size() - 1) {
                        break label68;
                    }

                    snakeBodyPos.add(((Circle)this.snake.get(snakeIndex)).getCenterY());
                    snakeBodyPos.add(((Circle)this.snake.get(snakeIndex)).getCenterX());
                    ((Circle)this.snake.get(snakeIndex)).setCenterY((Double)snakeBodyPos.poll());
                    ((Circle)this.snake.get(snakeIndex)).setCenterX((Double)snakeBodyPos.poll());
                    ++snakeIndex;
                }
            case left:
                if (this.getSnakeHead().getCenterX() == 0.0) {
                    this.getSnakeHead().setCenterX(500.0);
                }

                this.getSnakeHead().setCenterX(this.getSnakeHead().getCenterX() - (double)x);

                while(true) {
                    if (snakeIndex > this.snake.size() - 1) {
                        break label68;
                    }

                    snakeBodyPos.add(((Circle)this.snake.get(snakeIndex)).getCenterY());
                    snakeBodyPos.add(((Circle)this.snake.get(snakeIndex)).getCenterX());
                    ((Circle)this.snake.get(snakeIndex)).setCenterY((Double)snakeBodyPos.poll());
                    ((Circle)this.snake.get(snakeIndex)).setCenterX((Double)snakeBodyPos.poll());
                    ++snakeIndex;
                }
            case down:
                if (this.getSnakeHead().getCenterY() == 500.0) {
                    this.getSnakeHead().setCenterY(0.0);
                }

                this.getSnakeHead().setCenterY(this.getSnakeHead().getCenterY() + (double)x);

                while(true) {
                    if (snakeIndex > this.snake.size() - 1) {
                        break label68;
                    }

                    snakeBodyPos.add(((Circle)this.snake.get(snakeIndex)).getCenterY());
                    snakeBodyPos.add(((Circle)this.snake.get(snakeIndex)).getCenterX());
                    ((Circle)this.snake.get(snakeIndex)).setCenterY((Double)snakeBodyPos.poll());
                    ((Circle)this.snake.get(snakeIndex)).setCenterX((Double)snakeBodyPos.poll());
                    ++snakeIndex;
                }
            case up:
                if (this.getSnakeHead().getCenterY() == 0.0) {
                    this.getSnakeHead().setCenterY(500.0);
                }

                this.getSnakeHead().setCenterY(this.getSnakeHead().getCenterY() - (double)x);

                while(snakeIndex <= this.snake.size() - 1) {
                    snakeBodyPos.add(((Circle)this.snake.get(snakeIndex)).getCenterY());
                    snakeBodyPos.add(((Circle)this.snake.get(snakeIndex)).getCenterX());
                    ((Circle)this.snake.get(snakeIndex)).setCenterY((Double)snakeBodyPos.poll());
                    ((Circle)this.snake.get(snakeIndex)).setCenterX((Double)snakeBodyPos.poll());
                    ++snakeIndex;
                }
        }

        snakeBodyPos = null;
        snakeIndex = 0;
    }
}
