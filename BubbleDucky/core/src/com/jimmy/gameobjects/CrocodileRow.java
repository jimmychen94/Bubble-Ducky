package com.jimmy.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.jimmy.bdhelpers.AssetLoader;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jimmy on 2014-12-27.
 */
public class CrocodileRow extends Scrollable {

    private Random random;
    private Croc croc1, croc2, croc3;
    private ArrayList<Croc> crocList;
    private ArrayList<Integer> index, index2, index3;
    private int midX;
    private Rectangle rect1, rect2, rect3;
    private Circle circ1, circ2, circ3;
    private boolean isScored = false;

    private Animation leftCroc, rightCroc, leftCrocGGAnimation, rightCrocGGAnimation;
    private Animation leftBblCroc, rightBblCroc;
    private ArrayList<Animation> rightCrocs, leftCrocs;

    public CrocodileRow(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        random = new Random();
        index = new ArrayList<Integer>();
        index2 = new ArrayList<Integer>();
        index3 = new ArrayList<Integer>();
        rect1 = new Rectangle();
        rect2 = new Rectangle();
        rect3 = new Rectangle();
        circ1 = new Circle();
        circ2 = new Circle();
        circ3 = new Circle();

        croc1 = new Croc();
        croc2 = new Croc();
        croc3 = new Croc();
        crocList = new ArrayList<Croc>();
        crocList.add(croc1);
        crocList.add(croc2);
        crocList.add(croc3);

        initCrocAnimations();
    }

    public void initCrocAnimations() {
        rightCrocs = new ArrayList<Animation>();
        leftCrocs = new ArrayList<Animation>();
        leftCroc = AssetLoader.leftCrocAnimation;
        rightCroc = AssetLoader.rightCrocAnimation;
        leftCrocGGAnimation = AssetLoader.leftCrocGGAnimation;
        rightCrocGGAnimation = AssetLoader.rightCrocGGAnimation;
        leftBblCroc = AssetLoader.leftCrocBblAnimation; // actually looking left
        rightBblCroc = AssetLoader.rightCrocBblAnimation; // actually looking right
        rightCrocs.add(rightCroc);
        rightCrocs.add(rightBblCroc);
        leftCrocs.add(leftCroc);
        leftCrocs.add(leftBblCroc);
    }

    public void placeCrocs(float midPointX) {
        midX = (int) midPointX;
        listRefill();
        genCroc1();
        genCroc2();
        genCroc3();
        if (getScrollSpeed() >= 45) {
            placeCrocAnimHarder();
        }
        else {
            placeCrocAnim();
        }
    }

    public void placeCrocAnimHarder() {
        if (croc1.getDirection()) {
            croc1.setParams(rightCrocs.get(random.nextInt(rightCrocs.size())), 0, 0, 15, 15);
        } else {
            croc1.setParams(leftCrocs.get(random.nextInt(leftCrocs.size())), -2, 0, 15, 15);
        }
        if (croc2.getDirection()) {
            croc2.setParams(rightCrocs.get(random.nextInt(rightCrocs.size())), 0, 0, 15, 15);
        } else {
            croc2.setParams(leftCrocs.get(random.nextInt(leftCrocs.size())), -2, 0, 15, 15);
        }
        if (croc3.getDirection()) {
            croc3.setParams(rightCrocs.get(random.nextInt(rightCrocs.size())), 0, 0, 15, 15);
        } else {
            croc3.setParams(leftCrocs.get(random.nextInt(leftCrocs.size())), -2, 0, 15, 15);
        }
    }

    public void placeCrocAnim() {
        if (croc1.getDirection()) {
            croc1.setParams(rightCroc, 0, 0, 15, 15);
        } else {
            croc1.setParams(leftCroc, -2, 0, 15, 15);
        }
        if (croc2.getDirection()) {
            croc2.setParams(rightCroc, 0, 0, 15, 15);
        } else {
            croc2.setParams(leftCroc, -2, 0, 15, 15);
        }
        if (croc3.getDirection()) {
            croc3.setParams(rightCroc, 0, 0, 15, 15);
        } else {
            croc3.setParams(leftCroc, -2, 0, 15, 15);
        }
    }

    public void placeCrocAnimGG(int crocNum) {
        if (crocNum == 1) {
            if (croc1.getDirection()) {
                croc1.setParams(rightCrocGGAnimation, 0, -6, 29, 19);
            } else {
                croc1.setParams(leftCrocGGAnimation, -14, -6, 29, 19);
            }
        } else if (crocNum == 2) {
            if (croc2.getDirection()) {
                croc2.setParams(rightCrocGGAnimation, 0, -6, 29, 19);
            } else {
                croc2.setParams(leftCrocGGAnimation, -14, -6, 29, 19);
            }
        } else {
            if (croc3.getDirection()) {
                croc3.setParams(rightCrocGGAnimation, 0, -6, 29, 19);
            } else {
                croc3.setParams(leftCrocGGAnimation, -14, -6, 29, 19);
            }
        }
    }

    public void genCroc3() {
        croc3.setPos(index.get(random.nextInt(index.size())));
        if (index2.indexOf(croc3.getPos()) == 0) {
            if (index2.get(1) == 0) {
                index.remove((Integer) croc3.getPos());
                index2.set(index2.indexOf(croc3.getPos()), 0);
                genCroc3();
            } else {
                croc3.setDirection(true);
                index.remove(index.indexOf(index2.get(1)));
                index2.set(1, 0);
                index.remove((Integer) croc3.getPos());
                index2.set(index2.indexOf(croc3.getPos()), 0);
            }
        } else if (index2.indexOf(croc3.getPos()) == 7) {
            if (index2.get(6) == 0) {
                index.remove((Integer) croc3.getPos());
                index2.set(index2.indexOf(croc3.getPos()), 0);
                genCroc3();
            } else {
                croc3.setDirection(false);
                index.remove(index.indexOf(index2.get(6)));
                index2.set(6, 0);
                index.remove((Integer) croc3.getPos());
                index2.set(index2.indexOf(croc3.getPos()), 0);
            }
        } else {
            croc3.setDirection(random.nextBoolean());
            if (croc3.getDirection()) {
                if (index2.get(index2.indexOf(croc3.getPos()) + 1) != 0) {
                    index.remove((Integer) index2.get(index2.indexOf(croc3.getPos()) + 1));
                    index2.set((index2.indexOf(croc3.getPos()) + 1), 0);
                    index.remove((Integer) croc3.getPos());
                    index2.set(index2.indexOf(croc3.getPos()), 0);
                } else if (index2.get(index2.indexOf(croc3.getPos()) - 1) != 0) {
                    croc3.setDirection(false);
                    index.remove((Integer) index2.get(index2.indexOf(croc3.getPos()) - 1));
                    index2.set((index2.indexOf(croc3.getPos()) - 1), 0);
                    index.remove((Integer) croc3.getPos());
                    index2.set(index2.indexOf(croc3.getPos()), 0);
                }
                else {
                    index.remove((Integer) croc3.getPos());
                    index2.set(index2.indexOf(croc3.getPos()), 0);
                    genCroc3();
                }
            } else if (index2.get(index2.indexOf(croc3.getPos()) - 1) != 0) {
                index.remove((Integer) index2.get(index2.indexOf(croc3.getPos()) - 1));
                index2.set((index2.indexOf(croc3.getPos()) - 1), 0);
                index.remove((Integer) croc3.getPos());
                index2.set(index2.indexOf(croc3.getPos()), 0);
            } else if (index2.get(index2.indexOf(croc3.getPos()) + 1) != 0) {
                croc3.setDirection(true);
                index.remove((Integer) index2.get(index2.indexOf(croc3.getPos()) + 1));
                index2.set((index2.indexOf(croc3.getPos()) + 1), 0);
                index.remove((Integer) croc3.getPos());
                index2.set(index2.indexOf(croc3.getPos()), 0);
            } else {
                index.remove((Integer) croc3.getPos());
                index2.set(index2.indexOf(croc3.getPos()), 0);
                genCroc3();
            }
        }
    }

    public void genCroc2() {
        croc2.setPos(index.get(random.nextInt(index.size())));
        if (index2.indexOf(croc2.getPos()) == 0) {
            if (index2.get(1) == 0) {
                index.remove((Integer) croc2.getPos());
                index2.set(index2.indexOf(croc2.getPos()), 0);
                genCroc2();
            } else {
                croc2.setDirection(true);
                index.remove(index.indexOf(index2.get(1)));
                index2.set(1, 0);
                index.remove((Integer) croc2.getPos());
                index2.set(index2.indexOf(croc2.getPos()), 0);
            }
        } else if (index2.indexOf(croc2.getPos()) == 7) {
            if (index2.get(6) == 0) {
                index.remove((Integer) croc2.getPos());
                index2.set(index2.indexOf(croc2.getPos()), 0);
                genCroc2();
            } else {
                croc2.setDirection(false);
                index.remove(index.indexOf(index2.get(6)));
                index2.set(6, 0);
                index.remove((Integer) croc2.getPos());
                index2.set(index2.indexOf(croc2.getPos()), 0);
            }
        }
        else {
            croc2.setDirection(random.nextBoolean());
            if (croc2.getDirection()) {
                if (index2.get(index2.indexOf(croc2.getPos()) + 1) != 0) {
                    index.remove((Integer) index2.get(index2.indexOf(croc2.getPos()) + 1));
                    index2.set((index2.indexOf(croc2.getPos()) + 1), 0);
                    index.remove((Integer) croc2.getPos());
                    index2.set(index2.indexOf(croc2.getPos()), 0);
                } else if (index2.get(index2.indexOf(croc2.getPos()) - 1) != 0) {
                    croc2.setDirection(false);
                    index.remove((Integer) index2.get(index2.indexOf(croc2.getPos()) - 1));
                    index2.set((index2.indexOf(croc2.getPos()) - 1), 0);
                    index.remove((Integer) croc2.getPos());
                    index2.set(index2.indexOf(croc2.getPos()), 0);
                }
            } else if (index2.get(index2.indexOf(croc2.getPos()) - 1) != 0) {
                index.remove(index2.get(index2.indexOf(croc2.getPos()) - 1));
                index2.set((index2.indexOf(croc2.getPos()) - 1), 0);
                index.remove((Integer) croc2.getPos());
                index2.set(index2.indexOf(croc2.getPos()), 0);
            } else if (index2.get(index2.indexOf(croc2.getPos()) + 1) != 0) {
                croc2.setDirection(true);
                index.remove((Integer) index2.get(index2.indexOf(croc2.getPos()) + 1));
                index2.set((index2.indexOf(croc2.getPos()) + 1), 0);
                index.remove((Integer) croc2.getPos());
                index2.set(index2.indexOf(croc2.getPos()), 0);
            } else {
                index.remove((Integer) croc2.getPos());
                index2.set(index2.indexOf(croc2.getPos()), 0);
                genCroc2();
            }
        }
    }

    public void genCroc1() {
        croc1.setPos(index.get(random.nextInt(index.size())));
        if (croc1.getPos() == index.get(0)) { // if 1st position, must face right
            croc1.setDirection(true);
            index.remove(1);
            index2.set(1, 0);
        } else if (croc1.getPos() == index.get(7)) { // if 8th position, must face left
            croc1.setDirection(false);
            index.remove(6);
            index2.set(6,0);
        } else {
            croc1.setDirection(random.nextBoolean());
            if (croc1.getDirection()) {
                index.remove(index.indexOf(croc1.getPos()) + 1);
                index2.set(index2.indexOf(croc1.getPos())+1, 0);
            } else {
                index.remove(index.indexOf(croc1.getPos())-1);
                index2.set(index2.indexOf(croc1.getPos())-1, 0);
            }
        }
        index.remove((Integer) croc1.getPos());
        index2.set(index2.indexOf(croc1.getPos()), 0);
    }

    @Override
    public void reset(float newY) {
        super.reset(newY);
        isScored = false;
        index.clear();
        index2.clear();
        index3.clear();
        placeCrocs(midX);
    }

    public void listRefill() {
        index.add(midX-66);
        index.add(midX-49);
        index.add(midX-32);
        index.add(midX-15);
        index.add(midX+2);
        index.add(midX+19);
        index.add(midX+36);
        index.add(midX+53);

        index2.add(midX-66);
        index2.add(midX-49);
        index2.add(midX-32);
        index2.add(midX-15);
        index2.add(midX+2);
        index2.add(midX+19);
        index2.add(midX+36);
        index2.add(midX+53);

        index3.add(midX-66);
        index3.add(midX-49);
        index3.add(midX-32);
        index3.add(midX-15);
        index3.add(midX+2);
        index3.add(midX+19);
        index3.add(midX+36);
        index3.add(midX+53);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        // set up hitboxes for crocs
        if (croc1.getDirection()) {
            rect1.set(croc1.getPos(), getY()+7, 27, 4);
        } else {
            rect1.set(croc1.getPos()-15, getY()+7, 24, 4);
        }
        if (croc2.getDirection()) {
            rect2.set(croc2.getPos(), getY()+7, 27, 4);
        } else {
            rect2.set(croc2.getPos()-15, getY()+7, 24, 4);
        }
        if (croc3.getDirection()) {
            rect3.set(croc3.getPos(), getY()+7, 27, 4);
        } else {
            rect3.set(croc3.getPos()-15, getY()+7, 24, 4);
        }
        circ1.set(croc1.getPos()+6, getY()+7, 6);
        circ2.set(croc2.getPos()+6, getY()+7, 6);
        circ3.set(croc3.getPos()+6, getY()+7, 6);
    }

    public Circle getCirc1() {
        return circ1;
    }

    public Circle getCirc2() {
        return circ2;
    }

    public Circle getCirc3() {
        return circ3;
    }

    public Rectangle getRect1() {
        return rect1;
    }

    public Rectangle getRect2() {
        return rect2;
    }

    public Rectangle getRect3() {
        return rect3;
    }

    public Croc getCroc1() {
        return croc1;
    }

    public Croc getCroc2() {
        return croc2;
    }

    public Croc getCroc3() {
        return croc3;
    }

    public ArrayList<Integer> getIndex() {
        return index;
    }

    public ArrayList<Croc> getCrocList() {
        return crocList;
    }

    public boolean collides(Ducky ducky) {
        if (position.y + 13 > ducky.getY()) {
            if ((Intersector.overlaps(ducky.getBoundingCircle(), getRect1()))
                    || (Intersector.overlaps(ducky.getBoundingCircle(), getCirc1()))) {
                placeCrocAnimGG(1);
                return true;
            } else if ((Intersector.overlaps(ducky.getBoundingCircle(), getRect2()))
                    || (Intersector.overlaps(ducky.getBoundingCircle(), getCirc2())) ){
                placeCrocAnimGG(2);
                return true;
            } else if ((Intersector.overlaps(ducky.getBoundingCircle(), getRect3()))
                    || (Intersector.overlaps(ducky.getBoundingCircle(), getCirc3()))) {
                placeCrocAnimGG(3);
                return true;
            }
        } return false;
    }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean isScored) {
        this.isScored = isScored;
    }

    public void onRestart(float y, float scrollSpeed, int x) {
        position.y = y;
        velocity.y = scrollSpeed;
        index.clear();
        index2.clear();
        index3.clear();
        placeCrocs(x);
    }
}
