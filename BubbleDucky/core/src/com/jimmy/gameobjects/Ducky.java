package com.jimmy.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jimmy on 2014-12-18.
 */
public class Ducky {

    private Vector2 position;
    private float rotation;
    private int weight;
    private int leftRiverEdge;
    private int rightRiverEdge;

    private int width;
    private int height;
    private Circle boundingCircle;

    private int midPointX;
    private float originalX;

    public Ducky(float x, float y, int width, int height, int midPointX) {
        this.width = width;
        this.height = height;
        originalX = x;
        boundingCircle = new Circle();
        position = new Vector2(x,y);
        this.midPointX = midPointX;
        rotation = 0;
        weight = 0;
        leftRiverEdge = midPointX-68;
        rightRiverEdge = midPointX+68;
    }

    public void update(float delta)  {
        if (weight > 180) {
            weight = 180;
        }
        if (weight < -180) {
            weight = -180;
        }

        if (weight > 0) {
            weight -= 0.3*delta;
        }
        if (weight < 0) {
            weight += 0.3*delta;
        }
        rotation = weight/4;

        position.x += weight*delta;
        boundingCircle.set(position.x+6, position.y+11, 5);

        if (position.x >= rightRiverEdge-getWidth()) {
            position.x = rightRiverEdge-getWidth();
            weight *= -0.6;
        }
        if (position.x <= leftRiverEdge) {
            position.x = leftRiverEdge;
            weight *= -0.6;
        }

    }

    public void updateReady(float runTime) {
        position.x = 2*(float) Math.sin(7*runTime) + originalX;
    }

    public void onClickLeft() {
        weight-=42;
    }

    public void onClickRight() {
        weight+=42;
    }

    public float getY() {
        return position.y;
    }

    public float getX() {
        return position.x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public float getRotation() {
        return rotation;
    }

    public void onRestart(int x) {
        position.x = x;
        rotation = 0;
    }

}
