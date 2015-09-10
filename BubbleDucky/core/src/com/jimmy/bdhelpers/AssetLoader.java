package com.jimmy.bdhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Jimmy on 2014-12-21.
 */
public class AssetLoader {

    public static TextureAtlas textureAtlas;

    public static Animation duckyAnimation, leftWavesAnimation, rightWavesAnimation;
    public static Animation leftCrocAnimation, rightCrocAnimation, leftCrocGGAnimation, rightCrocGGAnimation,
            leftCrocBblAnimation, rightCrocBblAnimation;
    public static TextureRegion croc1,croc2,croc3,croc4,croc5,croc6,croc7,croc8;
    public static TextureRegion bblcroc1, bblcroc2, bblcroc3, bblcroc4, bblcroc5, bblcroc6, bblcroc7, bblcroc8;
    public static TextureRegion crocGG1, crocGG2, crocGG3, crocGG4, crocGG5, crocGG6,crocGG7, crocGG8;
    public static TextureRegion ducky1,ducky2,ducky3,ducky4;
    public static TextureRegion duckyLeftWaves1,duckyLeftWaves2,duckyLeftWaves3,duckyLeftWaves4,duckyLeftWaves5;
    public static TextureRegion duckyRightWaves1,duckyRightWaves2,duckyRightWaves3,duckyRightWaves4,duckyRightWaves5;
    public static TextureRegion leftRiverDirt,river,rightRiverDirt;
    public static TextureRegion jungleBanner;
    public static BitmapFont font, scoreText;
    public static Preferences prefs;
    public static TextureRegion logo;
    public static TextureRegion rulesPage;
    public static TextureRegion gameOverBox;
    public static TextureRegion helpButtonDown, helpButtonUp, playButtonDown, playButtonUp, backButtonDown, backButtonUp;
    public static Animation tapLeft, tapRight, title;
    public static TextureRegion tapLeft1, tapLeft2, tapRight1, tapRight2, title1, title2, title3;

    public static void load() {

        textureAtlas = new TextureAtlas(Gdx.files.internal("Spritesheets/DuckySprites.atlas"));

        // logo setup
        logo = textureAtlas.findRegion("Logo");

        // right crocodiles
        croc1 = textureAtlas.findRegion("Croc1");
        croc2 = textureAtlas.findRegion("Croc2");
        croc3 = textureAtlas.findRegion("Croc3");
        croc4 = textureAtlas.findRegion("Croc4");
        TextureRegion[] rightCrocFrames = {croc1, croc2, croc3, croc4};
        for (TextureRegion tr : rightCrocFrames) {
            tr.flip(false, true);
        }
        rightCrocAnimation = new Animation(0.15f, rightCrocFrames);
        rightCrocAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // right bubble crocodiles
        bblcroc1 = textureAtlas.findRegion("BubbleCroc5");
        bblcroc2 = textureAtlas.findRegion("BubbleCroc6");
        bblcroc3 = textureAtlas.findRegion("BubbleCroc7");
        bblcroc4 = textureAtlas.findRegion("BubbleCroc8");
        TextureRegion[] rightBblCrocFrames = {bblcroc1, bblcroc2, bblcroc3, bblcroc4};
        for (TextureRegion tr : rightBblCrocFrames) {
            tr.flip(false, true);
        }
        rightCrocBblAnimation = new Animation(0.15f, rightBblCrocFrames);
        rightCrocBblAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // left crocodiles
        croc5 = textureAtlas.findRegion("Croc5");
        croc6 = textureAtlas.findRegion("Croc6");
        croc7 = textureAtlas.findRegion("Croc7");
        croc8 = textureAtlas.findRegion("Croc8");
        TextureRegion[] leftCrocFrames = {croc5, croc6, croc7, croc8};
        for (TextureRegion tr : leftCrocFrames) {
            tr.flip(false, true);
        }
        leftCrocAnimation = new Animation(0.15f, leftCrocFrames);
        leftCrocAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // left bubble crocodiles
        bblcroc5 = textureAtlas.findRegion("BubbleCroc1");
        bblcroc6 = textureAtlas.findRegion("BubbleCroc2");
        bblcroc7 = textureAtlas.findRegion("BubbleCroc3");
        bblcroc8 = textureAtlas.findRegion("BubbleCroc4");
        TextureRegion[] leftBblCrocFrames = {bblcroc5, bblcroc6, bblcroc7, bblcroc8};
        for (TextureRegion tr : leftBblCrocFrames) {
            tr.flip(false, true);
        }
        leftCrocBblAnimation = new Animation(0.15f, leftBblCrocFrames);
        leftCrocBblAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // right croc game over
        crocGG1 = textureAtlas.findRegion("DeadDucky1");
        crocGG2 = textureAtlas.findRegion("DeadDucky2");
        crocGG3 = textureAtlas.findRegion("DeadDucky3");
        crocGG4 = textureAtlas.findRegion("DeadDucky4");
        TextureRegion[] rightCrocGGFrames = {crocGG1, crocGG2, crocGG3, crocGG4};
        for (TextureRegion tr : rightCrocGGFrames) {
            tr.flip(false, true);
        }
        rightCrocGGAnimation = new Animation(0.2f, rightCrocGGFrames);
        rightCrocGGAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // left croc game over
        crocGG5 = textureAtlas.findRegion("DeadDucky5");
        crocGG6 = textureAtlas.findRegion("DeadDucky6");
        crocGG7 = textureAtlas.findRegion("DeadDucky7");
        crocGG8 = textureAtlas.findRegion("DeadDucky8");
        TextureRegion[] leftCrocGGFrames = {crocGG5, crocGG6, crocGG7, crocGG8};
        for (TextureRegion tr : leftCrocGGFrames) {
            tr.flip(false, true);
        }
        leftCrocGGAnimation = new Animation(0.2f, leftCrocGGFrames);
        leftCrocGGAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // main duck
        ducky1 = textureAtlas.findRegion("Ducky1");
        ducky2 = textureAtlas.findRegion("Ducky2");
        ducky3 = textureAtlas.findRegion("Ducky3");
        ducky4 = textureAtlas.findRegion("Ducky4");
        TextureRegion[] duckyFrames = {ducky1, ducky2, ducky3, ducky4};
        for (TextureRegion tr : duckyFrames) {
            tr.flip(false, true);
        }
        duckyAnimation = new Animation(0.2f, duckyFrames);
        duckyAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // left duck waves
        duckyLeftWaves1 = textureAtlas.findRegion("DuckyWaves1");
        duckyLeftWaves2 = textureAtlas.findRegion("DuckyWaves2");
        duckyLeftWaves3 = textureAtlas.findRegion("DuckyWaves3");
        duckyLeftWaves4 = textureAtlas.findRegion("DuckyWaves4");
        duckyLeftWaves5 = textureAtlas.findRegion("DuckyWaves5");
        TextureRegion[] duckyLeftWaves = {duckyLeftWaves1,duckyLeftWaves2,duckyLeftWaves3,duckyLeftWaves4,duckyLeftWaves5};
        for (TextureRegion tr : duckyLeftWaves) {
            tr.flip(false, true);
        }
        leftWavesAnimation = new Animation(0.08f, duckyLeftWaves);
        leftWavesAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // right duck waves
        duckyRightWaves1 = textureAtlas.findRegion("DuckyWaves6");
        duckyRightWaves2 = textureAtlas.findRegion("DuckyWaves7");
        duckyRightWaves3 = textureAtlas.findRegion("DuckyWaves8");
        duckyRightWaves4 = textureAtlas.findRegion("DuckyWaves9");
        duckyRightWaves5 = textureAtlas.findRegion("DuckyWaves10");
        TextureRegion[] duckyRightWaves = {duckyRightWaves1,duckyRightWaves2,duckyRightWaves3,duckyRightWaves4,duckyRightWaves5};
        for (TextureRegion tr : duckyRightWaves) {
            tr.flip(false,true);
        }
        rightWavesAnimation = new Animation(0.08f, duckyRightWaves);
        rightWavesAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // river
        river = textureAtlas.findRegion("RiverWater");
        river.flip(false,true);

        // left dirt
        leftRiverDirt = textureAtlas.findRegion("LeftDirt");
        leftRiverDirt.flip(false,true);

        // right dirt
        rightRiverDirt = textureAtlas.findRegion("RightDirt");
        rightRiverDirt.flip(false,true);

        // jungle banner
        jungleBanner = textureAtlas.findRegion("JungleBanner");
        jungleBanner.flip(false,true);

        // font setup
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(.3f, -.3f);
        scoreText = new BitmapFont(Gdx.files.internal("data/score.fnt"));
        scoreText.setScale(.35f, -.35f);

        // menu and buttons setup
        rulesPage = textureAtlas.findRegion("GameRules");
        rulesPage.flip(false,true);
        helpButtonDown = textureAtlas.findRegion("HelpButtonDown");
        helpButtonDown.flip(false,true);
        helpButtonUp = textureAtlas.findRegion("HelpButtonUp");
        helpButtonUp.flip(false,true);
        playButtonDown = textureAtlas.findRegion("PlayButtonDown");
        playButtonDown.flip(false,true);
        playButtonUp = textureAtlas.findRegion("PlayButtonUp");
        playButtonUp.flip(false,true);
        backButtonUp = textureAtlas.findRegion("BackButtonUp");
        backButtonUp.flip(false,true);
        backButtonDown = textureAtlas.findRegion("BackButtonDown");
        backButtonDown.flip(false,true);

        tapLeft1 = textureAtlas.findRegion("TapLeftFinger1");
        tapLeft2 = textureAtlas.findRegion("TapLeftFinger2");
        TextureRegion[] tapLeftFrames = {tapLeft1, tapLeft2};
        for (TextureRegion tr : tapLeftFrames) {
            tr.flip(false,true);
        }
        tapLeft = new Animation(0.3f, tapLeftFrames);
        tapLeft.setPlayMode(Animation.PlayMode.LOOP);

        tapRight1 = textureAtlas.findRegion("TapRightFinger1");
        tapRight2 = textureAtlas.findRegion("TapRightFinger2");
        TextureRegion[] tapRightFrames = {tapRight1, tapRight2};
        for (TextureRegion tr : tapRightFrames) {
            tr.flip(false, true);
        }
        tapRight = new Animation(0.3f, tapRightFrames);
        tapRight.setPlayMode(Animation.PlayMode.LOOP);

        title1 = textureAtlas.findRegion("Title1");
        title2 = textureAtlas.findRegion("Title2");
        title3 = textureAtlas.findRegion("Title3");
        TextureRegion[] titleFrames = {title1, title2, title3};
        for (TextureRegion tr : titleFrames) {
            tr.flip(false,true);
        }
        title = new Animation(0.2f, titleFrames);
        title.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        gameOverBox = textureAtlas.findRegion("GameOverMenu");
        gameOverBox.flip(false, true);

        // creates or retrieves existing prefs file
        prefs = Gdx.app.getPreferences("BubbleDucky");

        // provide default high score of 0
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        textureAtlas.dispose();
        font.dispose();
    }
}
