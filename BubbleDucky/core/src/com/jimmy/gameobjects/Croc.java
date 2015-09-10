package com.jimmy.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by Jimmy on 2014-12-30.
 */
public class Croc {
    private int pos;
    private int x, y, width, height; // used for calculating rendered position
    private boolean direction;
    private Animation animation;

    public void setParams(Animation animation, int x, int y, int width, int height) {
        this.animation = animation;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getPos() {
        return pos;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getDirection() {
        return direction;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDirection(boolean direction1) {
        this.direction = direction1;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
