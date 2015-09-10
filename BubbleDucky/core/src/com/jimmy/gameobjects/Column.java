package com.jimmy.gameobjects;

/**
 * Created by Jimmy on 2014-12-27.
 */
public class Column extends Scrollable {

    public Column(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

    public void onRestart(float y, float scrollSpeed) {
        position.y = y;
        velocity.y = scrollSpeed;
    }
}
