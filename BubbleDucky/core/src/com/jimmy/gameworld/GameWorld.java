package com.jimmy.gameworld;

import com.jimmy.bdhelpers.AssetLoader;
import com.jimmy.gameobjects.Ducky;
import com.jimmy.gameobjects.ScrollHandler;

/**
 * Created by Jimmy on 2014-12-18.
 */
public class GameWorld {

    private Ducky ducky;
    private ScrollHandler scroller;
    private int score = 0;
    private int midPointX;
    private float runTime = 0;

    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER, MENU
    }

    public GameWorld(int midPointX) {
        this.midPointX = midPointX;
        currentState = GameState.MENU;
        scroller = new ScrollHandler(this, midPointX);
        ducky = new Ducky(midPointX-6,207,13,19, midPointX);
    }

    public void update(float delta) {
        runTime+= delta;

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;
            case MENU:
                updateMenu(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
    }

    private void updateReady(float delta) {
        scroller.updateReady(delta);
    }

    private void updateMenu(float delta) {
        ducky.updateReady(runTime);
        scroller.updateReady(delta);
    }

    private void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        ducky.update(delta);
        scroller.update(delta);

        if (scroller.collides(ducky)) {
            ducky.setWeight(0);
            scroller.stop();
            currentState = GameState.GAMEOVER;

               if (score > AssetLoader.getHighScore()) {
                   AssetLoader.setHighScore(score);
               }
        }
    }

    public int getMidPointX() {
        return midPointX;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public Ducky getDucky() {
        return ducky;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score+=increment;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void menu() {
        score = 0;
        ducky.onRestart(midPointX-6);
        scroller.onRestart();
        currentState = GameState.MENU;
    }

    public void ready() {
        currentState = GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        ducky.onRestart(midPointX-6);
        scroller.onRestart();
        currentState = GameState.READY;
    }
}
