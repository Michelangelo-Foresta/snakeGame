//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.snake;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Fruit extends Circle {
    private Circle circle;

    public Fruit() {
        int from = 0;
        int to = 500;
        int multi = 10;
        Random rand = new Random();
        double ranX = (double)(multi * (Math.round((float)(rand.nextInt(to + multi - from) + from)) / multi));
        double ranY = (double)(multi * (Math.round((float)(rand.nextInt(to + multi - from) + from)) / multi));
        this.circle = new Circle(ranX, ranY, 5.0);
        this.circle.setFill(Color.RED);
    }

    public void newFruit() {
        int from = 0;
        int to = 500;
        int multi = 10;
        Random rand = new Random();
        double ranX = (double)(multi * (Math.round((float)(rand.nextInt(to + multi - from) + from)) / multi));
        double ranY = (double)(multi * (Math.round((float)(rand.nextInt(to + multi - from) + from)) / multi));
        this.circle.setCenterX(ranX);
        this.circle.setCenterY(ranY);
        this.circle.setFill(Color.RED);
    }

    public Circle getFruit() {
        return this.circle;
    }

    public double[] getPos() {
        return new double[]{this.circle.getCenterX(), this.circle.getCenterY()};
    }
}
