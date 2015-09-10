package com.jimmy.bdhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.jimmy.gameobjects.Ducky;
import com.jimmy.gameworld.GameRenderer;
import com.jimmy.gameworld.GameWorld;
import com.jimmy.ui.SimpleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 2014-12-21.
 */
public class InputHandler implements InputProcessor{

    private Ducky myDucky;
    private int midPointX;
    private GameWorld gameWorld;

    private List<SimpleButton> menuButtons, gameOverButtons;
    private SimpleButton playButton, helpButton, replayButton, hintButton, backButton;

    private float scaleFactorX;
    private float scaleFactorY;

    public static boolean helpMenuOpen;

    public InputHandler(Ducky ducky, GameWorld world, float factorX, float factorY) {
        scaleFactorX = factorX;
        scaleFactorY = factorY;
        gameWorld = world;

        myDucky = ducky;
        midPointX = gameWorld.getMidPointX();

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(midPointX-19, 130, 38, 30, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        helpButton = new SimpleButton(midPointX-19, 175, 38, 20, AssetLoader.helpButtonUp, AssetLoader.helpButtonDown);
        menuButtons.add(playButton);
        menuButtons.add(helpButton);

        gameOverButtons = new ArrayList<SimpleButton>();
        backButton = new SimpleButton(midPointX-6-38, 168, 38, 20, AssetLoader.backButtonUp, AssetLoader.backButtonDown);
        hintButton = new SimpleButton(midPointX+6, 168, 38, 20, AssetLoader.helpButtonUp, AssetLoader.helpButtonDown);
        replayButton = new SimpleButton(midPointX-19, 225, 38, 30, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        gameOverButtons.add(backButton);
        gameOverButtons.add(hintButton);
        gameOverButtons.add(replayButton);
        helpMenuOpen = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (isHelpMenuOpen()) {
           // nothing
        } else if (gameWorld.isMenu()) {
            playButton.isTouchDown(screenX, screenY);
            helpButton.isTouchDown(screenX, screenY);
        } else if (gameWorld.isReady()) {
            if (screenY > 180) {
                if (screenX < midPointX-5) {
                    gameWorld.start();
                    myDucky.onClickLeft();
                }
                if (screenX > midPointX+5) {
                    gameWorld.start();
                    myDucky.onClickRight();
                }
            }
        } else if(gameWorld.isGameOver()) {
            replayButton.isTouchDown(screenX, screenY);
            hintButton.isTouchDown(screenX, screenY);
            backButton.isTouchDown(screenX, screenY);
        }  else if (screenY > 180) {
            if (screenX < midPointX-5) {
                myDucky.onClickLeft();
            }
            if (screenX > midPointX+5) {
                myDucky.onClickRight();
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (isHelpMenuOpen()) {
            setHelpMenuOpen(false);
        } else if (gameWorld.isMenu()) {
            if (playButton.isTouchUp(screenX, screenY)) {
                gameWorld.ready();
                return true;
            } else if (helpButton.isTouchUp(screenX, screenY)) {
                setHelpMenuOpen(true);
                return true;
            }
        } else if (gameWorld.isGameOver()) {
            if (replayButton.isTouchUp(screenX, screenY)) {
                gameWorld.restart();
                return true;
            } else if (hintButton.isTouchUp(screenX, screenY)) {
                setHelpMenuOpen(true);
                return true;
            } else if (backButton.isTouchUp(screenX, screenY)) {
                gameWorld.menu();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX/scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY/scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }

    public List<SimpleButton> getGameOverButtons() {
        return gameOverButtons;
    }

    public static boolean isHelpMenuOpen() {
        return helpMenuOpen;
    }

    public static void setHelpMenuOpen(boolean bool) {
        helpMenuOpen = bool;
    }
}
