package com.jimmy.bubbleducky;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.jimmy.bdhelpers.AssetLoader;
import com.jimmy.screens.GameScreen;
import com.jimmy.screens.SplashScreen;

public class BDGame extends Game {

    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new SplashScreen(this));
        //   setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
