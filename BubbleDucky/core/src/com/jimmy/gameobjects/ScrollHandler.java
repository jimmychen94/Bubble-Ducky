package com.jimmy.gameobjects;

import com.jimmy.gameworld.GameWorld;

/**
 * Created by Jimmy on 2014-12-27.
 */
public class ScrollHandler {

    private Column river1, river2;
    private Column leftDirt1, leftDirt2, rightDirt1, rightDirt2;
    private CrocodileRow crocRow1, crocRow2, crocRow3;
    public static int SCROLL_SPEED = 40;
    public static final int CROC_GAP = 90;
    private GameWorld gameWorld;
    private int midPointX;

    public ScrollHandler(GameWorld gameWorld, float xPos) {
        this.gameWorld = gameWorld;
        midPointX = (int) xPos;
        river1 = new Column(xPos-68, 0, 136, 276, SCROLL_SPEED);
        river2 = new Column(xPos-68, river1.getTailY(), 136, 276, SCROLL_SPEED);

        leftDirt1 = new Column(xPos-77, 0, 9, 276, SCROLL_SPEED);
        leftDirt2 = new Column(xPos-77, leftDirt1.getTailY(), 9, 276, SCROLL_SPEED);
        rightDirt1 = new Column(xPos+68, 0, 9, 276, SCROLL_SPEED);
        rightDirt2 = new Column(xPos+68, rightDirt1.getTailY(), 9, 276, SCROLL_SPEED);

        crocRow1 = new CrocodileRow(xPos-68, -CROC_GAP, 136, 13, SCROLL_SPEED);
        crocRow1.placeCrocs(xPos);
        crocRow2 = new CrocodileRow(xPos-68, crocRow1.getTailY()-CROC_GAP, 136, 13, SCROLL_SPEED);
        crocRow2.placeCrocs(xPos);
        crocRow3 = new CrocodileRow(xPos-68, crocRow2.getTailY()-CROC_GAP, 136, 13, SCROLL_SPEED);
        crocRow3.placeCrocs(xPos);
    }

    public void update(float delta) {
        river1.update(delta);
        river2.update(delta);
        leftDirt1.update(delta);
        leftDirt2.update(delta);
        rightDirt1.update(delta);
        rightDirt2.update(delta);
        crocRow1.update(delta);
        crocRow2.update(delta);
        crocRow3.update(delta);

        if (river1.isScrolledBottom()) {
            river1.reset(river2.getTailY());
        } else if (river2.isScrolledBottom()) {
            river2.reset(river1.getTailY());
        }

        if (leftDirt1.isScrolledBottom()) {
            leftDirt1.reset(leftDirt2.getTailY());
        } else if (leftDirt2.isScrolledBottom()) {
            leftDirt2.reset(leftDirt1.getTailY());
        }

        if (rightDirt1.isScrolledBottom()) {
            rightDirt1.reset(rightDirt2.getTailY());
        } else if (rightDirt2.isScrolledBottom()) {
            rightDirt2.reset(rightDirt1.getTailY());
        }

        if (crocRow1.isScrolledBottom()) {
            crocRow1.reset(crocRow3.getTailY() - CROC_GAP);
            increaseGameSpeed();
        } else if (crocRow2.isScrolledBottom()) {
            crocRow2.reset(crocRow1.getTailY() - CROC_GAP);
            increaseGameSpeed();
        } else if (crocRow3.isScrolledBottom()) {
            crocRow3.reset(crocRow2.getTailY() - CROC_GAP);
            increaseGameSpeed();
        }
    }

    public void updateReady(float delta) {
        river1.update(delta);
        river2.update(delta);
        leftDirt1.update(delta);
        leftDirt2.update(delta);
        rightDirt1.update(delta);
        rightDirt2.update(delta);

        if (river1.isScrolledBottom()) {
            river1.reset(river2.getTailY());
        } else if (river2.isScrolledBottom()) {
            river2.reset(river1.getTailY());
        }

        if (leftDirt1.isScrolledBottom()) {
            leftDirt1.reset(leftDirt2.getTailY());
        } else if (leftDirt2.isScrolledBottom()) {
            leftDirt2.reset(leftDirt1.getTailY());
        }

        if (rightDirt1.isScrolledBottom()) {
            rightDirt1.reset(rightDirt2.getTailY());
        } else if (rightDirt2.isScrolledBottom()) {
            rightDirt2.reset(rightDirt1.getTailY());
        }
    }

    public void increaseGameSpeed() {
        if (crocRow1.getScrollSpeed() >= 79) {
            // do nothing
        } else if (gameWorld.getScore() != 0 && gameWorld.getScore() % 3 == 0) {
            river1.addScrollSpeed(3);
            river2.addScrollSpeed(3);
            leftDirt1.addScrollSpeed(3);
            leftDirt2.addScrollSpeed(3);
            rightDirt1.addScrollSpeed(3);
            rightDirt2.addScrollSpeed(3);
            crocRow1.addScrollSpeed(3);
            crocRow2.addScrollSpeed(3);
            crocRow3.addScrollSpeed(3);
        }
    }

    public Column getRiver1() {
        return river1;
    }

    public Column getRiver2() {
        return river2;
    }

    public Column getLeftDirt1() {
        return leftDirt1;
    }

    public Column getLeftDirt2() {
        return leftDirt2;
    }

    public Column getRightDirt1() {
        return rightDirt1;
    }

    public Column getRightDirt2() {
        return rightDirt2;
    }

    public CrocodileRow getCrocRow1() {
        return crocRow1;
    }

    public CrocodileRow getCrocRow2() {
        return crocRow2;
    }

    public CrocodileRow getCrocRow3() {
        return crocRow3;
    }

    public void stop() {
        river1.stop();
        river2.stop();
        leftDirt1.stop();
        leftDirt2.stop();
        rightDirt1.stop();
        rightDirt2.stop();
        crocRow1.stop();
        crocRow2.stop();
        crocRow3.stop();
    }

    public boolean collides(Ducky ducky) {
        if (!crocRow1.isScored() && crocRow1.getY() > ducky.getY() + ducky.getHeight()-2) {
            addScore(1);
            crocRow1.setScored(true);
        } else if (!crocRow2.isScored() && crocRow2.getY() > ducky.getY() + ducky.getHeight()-2) {
            addScore(1);
            crocRow2.setScored(true);
        } else if (!crocRow3.isScored() && crocRow3.getY() > ducky.getY() + ducky.getHeight()-2) {
            addScore(1);
            crocRow3.setScored(true);
        }
        return (crocRow1.collides(ducky)||crocRow2.collides(ducky)||crocRow3.collides(ducky));
    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }

    public void onRestart() {
        river1.onRestart(0, SCROLL_SPEED);
        river2.onRestart(river1.getTailY(), SCROLL_SPEED);
        leftDirt1.onRestart(0, SCROLL_SPEED);
        leftDirt2.onRestart(leftDirt1.getTailY(), SCROLL_SPEED);
        rightDirt1.onRestart(0, SCROLL_SPEED);
        rightDirt2.onRestart(rightDirt1.getTailY(), SCROLL_SPEED);
        crocRow1.onRestart(-CROC_GAP, SCROLL_SPEED, midPointX);
        crocRow2.onRestart(crocRow1.getTailY()-CROC_GAP, SCROLL_SPEED, midPointX);
        crocRow3.onRestart(crocRow2.getTailY()-CROC_GAP, SCROLL_SPEED, midPointX);
    }
}
