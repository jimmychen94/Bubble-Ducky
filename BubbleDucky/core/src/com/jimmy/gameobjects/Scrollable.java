package com.jimmy.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jimmy on 2014-12-27.
 */
public class Scrollable {

    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledBottom;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, scrollSpeed);
        this.width = width;
        this.height = height;
        isScrolledBottom = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        if (position.y > 276) {
            isScrolledBottom = true;
        }
    }

    public void reset(float newY) {
        position.y = newY;
        isScrolledBottom = false;
    }

    public void stop() {
        velocity.y = 0;
    }

    public float getTailY() {
        return position.y - height;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isScrolledBottom() {
        return isScrolledBottom;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getScrollSpeed() {
        return velocity.y;
    }

    public void addScrollSpeed(float velocity) {
        this.velocity.y += velocity;
    }
}
