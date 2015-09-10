package com.jimmy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.jimmy.bdhelpers.InputHandler;
import com.jimmy.gameworld.GameRenderer;
import com.jimmy.gameworld.GameWorld;

/**
 * Created by Jimmy on 2014-12-18.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameHeight = 276;
        float gameWidth = gameHeight / (screenHeight / screenWidth);
        int midPointX = (int) (gameWidth/2);

        world = new GameWorld(midPointX);

        Gdx.input.setInputProcessor(new InputHandler(world.getDucky(), world, screenWidth/gameWidth, screenHeight/gameHeight));

        renderer = new GameRenderer(world, (int) gameWidth, midPointX);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        runTime+= delta;
        world.update(delta);
        renderer.render(delta, runTime);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
