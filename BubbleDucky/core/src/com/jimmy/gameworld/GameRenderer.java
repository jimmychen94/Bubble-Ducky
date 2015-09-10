package com.jimmy.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jimmy.bdhelpers.AssetLoader;
import com.jimmy.bdhelpers.InputHandler;
import com.jimmy.gameobjects.Column;
import com.jimmy.gameobjects.Croc;
import com.jimmy.gameobjects.CrocodileRow;
import com.jimmy.gameobjects.Ducky;
import com.jimmy.gameobjects.ScrollHandler;
import com.jimmy.ui.SimpleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 2013-12-18.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    private int gameWidth;
    private int midPointX;
    private int bannerRepeat;

    // game objects
    private Ducky ducky;
    private ScrollHandler scroller;
    private Column river1, river2;
    private Column leftDirt1, leftDirt2, rightDirt1, rightDirt2;
    private CrocodileRow crocRow1, crocRow2, crocRow3;
    private ArrayList<CrocodileRow> crocRows;

    // game assets
    private Animation duckyAnimation, leftWavesAnimation, rightWavesAnimation;
    private TextureRegion river, leftRiverDirt, rightRiverDirt;
    private TextureRegion jungleBanner;
    private Animation tapLeft, tapRight, titleAnimation;
    private TextureRegion rulesPage, gameOverPage;

    // buttons
    private List<SimpleButton> menuButtons;
    private List<SimpleButton> gameOverButtons;

    public GameRenderer(GameWorld world, int gameWidth, int midPointX) {
        this.gameWidth = gameWidth;
        this.midPointX = midPointX;

        menuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getMenuButtons();
        gameOverButtons = ((InputHandler) Gdx.input.getInputProcessor()).getGameOverButtons();
        myWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, 276);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        crocRows = new ArrayList<CrocodileRow>();

        // calculate how many times we need to draw banner
        int remainder = gameWidth;
        while (remainder > 0) {
            remainder -= 108;
            bannerRepeat++;
        }

        initGameObjects();
    }

    private void initGameObjects() {
        scroller = myWorld.getScroller();

        ducky = myWorld.getDucky();
        duckyAnimation = AssetLoader.duckyAnimation;
        leftWavesAnimation = AssetLoader.leftWavesAnimation;
        rightWavesAnimation = AssetLoader.rightWavesAnimation;

        river1 = scroller.getRiver1();
        river2 = scroller.getRiver2();
        river = AssetLoader.river;

        leftDirt1 = scroller.getLeftDirt1();
        leftDirt2 = scroller.getLeftDirt2();
        leftRiverDirt = AssetLoader.leftRiverDirt;

        rightDirt1 = scroller.getRightDirt1();
        rightDirt2 = scroller.getRightDirt2();
        rightRiverDirt = AssetLoader.rightRiverDirt;

        crocRow1 = scroller.getCrocRow1();
        crocRow2 = scroller.getCrocRow2();
        crocRow3 = scroller.getCrocRow3();
        crocRows.add(crocRow1);
        crocRows.add(crocRow2);
        crocRows.add(crocRow3);

        jungleBanner = AssetLoader.jungleBanner;

        tapLeft = AssetLoader.tapLeft;
        tapRight = AssetLoader.tapRight;
        titleAnimation = AssetLoader.title;
        rulesPage = AssetLoader.rulesPage;
        gameOverPage = AssetLoader.gameOverBox;
    }

    public void render(float delta, float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.disableBlending();
        drawRiver();

        batch.enableBlending();
        if (myWorld.isGameOver()) {
            // don't draw ducky
        } else {
            drawDucky(runTime);
        }

        if (myWorld.isMenu()) {
            // dont draw crocs
        } else {
            drawCrocs(runTime);
        }

        batch.disableBlending();
        drawDirt();
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // excess grass
        shapeRenderer.setColor(69 / 255.0f, 183 / 255.0f, 60 / 255.0f, 1);
        shapeRenderer.rect(0, 0, midPointX -76, 276);
        shapeRenderer.rect(midPointX + 76, 0, gameWidth, 276);

        //drawHitboxes();
        shapeRenderer.end();

        batch.begin();
        batch.enableBlending();
        // draw banner
        for (int i = 0; i<bannerRepeat; i++) {
            batch.draw(jungleBanner, 0+(i*108), 0, 108, 41);
        }

        if (myWorld.isRunning()) {
            drawScore();
        } else if (myWorld.isReady()) {
            drawInstructions(runTime);
            drawScore();
        } else if (myWorld.isMenu()) {
            drawMenu(runTime);
        } else if (myWorld.isGameOver()) {
            drawGameOverMenu();
            drawScore();
        }
        batch.end();
        if (InputHandler.isHelpMenuOpen()) {
            drawHelpMenu();
        }
    }

    public void drawHelpMenu() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 0.5f);
        shapeRenderer.rect(0,0,gameWidth, 276);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();
        batch.draw(rulesPage, midPointX-69, 60, 138, 150);
        batch.end();
    }

    public void drawGameOverMenu() {
        batch.draw(gameOverPage, midPointX-47, 55, 93, 100);
        String highScore = AssetLoader.getHighScore()+"";
        AssetLoader.scoreText.draw(batch, highScore, midPointX+11, 107);
        String score = myWorld.getScore()+"";
        AssetLoader.scoreText.draw(batch, score, midPointX+11, 133);
        for (SimpleButton b : gameOverButtons) {
            b.draw(batch);
        }
    }

    public void drawMenu(float runTime) {
        batch.draw(titleAnimation.getKeyFrame(runTime), midPointX-60, 50, 120, 62);
        for (SimpleButton b : menuButtons) {
            b.draw(batch);
        }
    }

    public void drawInstructions(float runTime) {
        batch.draw(tapLeft.getKeyFrame(runTime), midPointX-46, 215, 14, 30);
        runTime++;
        batch.draw(tapRight.getKeyFrame(runTime), midPointX+32, 215, 14, 30);
    }

    public void drawScore() {
        String score = myWorld.getScore()+"";
        AssetLoader.font.draw(batch, "" + myWorld.getScore(), midPointX - (4 * score.length()), 10);
    }

    public void drawRiver(){
        batch.draw(river, river1.getX(), river1.getY(), river1.getWidth(), river1.getHeight());
        batch.draw(river, river2.getX(), river2.getY(), river2.getWidth(), river2.getHeight());
    }

    public void drawDucky(float runTime) {
        batch.draw(duckyAnimation.getKeyFrame(runTime),ducky.getX(), ducky.getY(),
                ducky.getWidth()/2.0f, ducky.getHeight()/2.0f,
                ducky.getWidth(), ducky.getHeight(), 1, 1, ducky.getRotation());

        batch.draw(leftWavesAnimation.getKeyFrame(runTime), ducky.getX() - 12, ducky.getY() + 12,
                12 + (ducky.getWidth() / 2.0f), 0,
                12, 30, 1, 1, ducky.getRotation());

        batch.draw(rightWavesAnimation.getKeyFrame(runTime), ducky.getX() + ducky.getWidth(), ducky.getY() + 12,
                0 - (ducky.getWidth() / 2.0f), 0,
                12, 30, 1, 1, ducky.getRotation());

    }

    public void drawCrocs(float runTime) {
        for (CrocodileRow cr : crocRows) {
            runTime++;
            for (Croc c: cr.getCrocList()) {
                batch.draw(c.getAnimation().getKeyFrame(runTime), c.getPos()+c.getX(), cr.getY()+c.getY(), c.getWidth(), c.getHeight());
            }
        }
    }

    public void drawDirt() {
        batch.draw(leftRiverDirt, leftDirt1.getX(), leftDirt1.getY(), leftDirt1.getWidth(), leftDirt1.getHeight());
        batch.draw(leftRiverDirt, leftDirt2.getX(), leftDirt2.getY(), leftDirt2.getWidth(), leftDirt2.getHeight());
        batch.draw(rightRiverDirt, rightDirt1.getX(), rightDirt1.getY(), rightDirt1.getWidth(), rightDirt1.getHeight());
        batch.draw(rightRiverDirt, rightDirt2.getX(), rightDirt2.getY(), rightDirt2.getWidth(), rightDirt2.getHeight());
    }

    public void drawHitboxes() {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(ducky.getBoundingCircle().x, ducky.getBoundingCircle().y, ducky.getBoundingCircle().radius);

        shapeRenderer.rect(crocRow1.getRect1().x, crocRow1.getRect1().y, crocRow1.getRect1().width, crocRow1.getRect1().height);
        shapeRenderer.rect(crocRow1.getRect2().x, crocRow1.getRect2().y, crocRow1.getRect2().width, crocRow1.getRect2().height);
        shapeRenderer.rect(crocRow1.getRect3().x, crocRow1.getRect3().y, crocRow1.getRect3().width, crocRow1.getRect3().height);

        shapeRenderer.rect(crocRow2.getRect1().x, crocRow2.getRect1().y, crocRow2.getRect1().width, crocRow2.getRect1().height);
        shapeRenderer.rect(crocRow2.getRect2().x, crocRow2.getRect2().y, crocRow2.getRect2().width, crocRow2.getRect2().height);
        shapeRenderer.rect(crocRow2.getRect3().x, crocRow2.getRect3().y, crocRow2.getRect3().width, crocRow2.getRect3().height);

        shapeRenderer.rect(crocRow3.getRect1().x, crocRow3.getRect1().y, crocRow3.getRect1().width, crocRow3.getRect1().height);
        shapeRenderer.rect(crocRow3.getRect2().x, crocRow3.getRect2().y, crocRow3.getRect2().width, crocRow3.getRect2().height);
        shapeRenderer.rect(crocRow3.getRect3().x, crocRow3.getRect3().y, crocRow3.getRect3().width, crocRow3.getRect3().height);

        // circles
        shapeRenderer.circle(crocRow1.getCirc1().x, crocRow1.getCirc1().y, crocRow1.getCirc1().radius);
        shapeRenderer.circle(crocRow1.getCirc2().x, crocRow1.getCirc2().y, crocRow1.getCirc2().radius);
        shapeRenderer.circle(crocRow1.getCirc3().x, crocRow1.getCirc3().y, crocRow1.getCirc3().radius);

        shapeRenderer.circle(crocRow2.getCirc1().x, crocRow2.getCirc1().y, crocRow2.getCirc1().radius);
        shapeRenderer.circle(crocRow2.getCirc2().x, crocRow2.getCirc2().y, crocRow2.getCirc2().radius);
        shapeRenderer.circle(crocRow2.getCirc3().x, crocRow2.getCirc3().y, crocRow2.getCirc3().radius);

        shapeRenderer.circle(crocRow3.getCirc1().x, crocRow3.getCirc1().y, crocRow3.getCirc1().radius);
        shapeRenderer.circle(crocRow3.getCirc2().x, crocRow3.getCirc2().y, crocRow3.getCirc2().radius);
        shapeRenderer.circle(crocRow3.getCirc3().x, crocRow3.getCirc3().y, crocRow3.getCirc3().radius);
    }
}
