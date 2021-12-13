package brick;

import ball.Ball;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/*
 * Brick Class
 */
abstract public class Brick  {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private static Random rnd;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;
    /**
     * Handle the design of the brick including its appearance
     * @param name type of the brick
     * @param pos position of the brick
     * @param size size of the brick
     * @param border colour of the brick's border
     * @param inner colour of the inner part of the brick
     * @param strength number of impacts that the brick have to deal with before being destroyed
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
    }

    /**
     * generate the size of the brick which will be implemented by the other types of bricks class
     * @param pos position of the brick
     * @param size size of the brick
     * @return return the shape of the brick
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }


     /**
     * get the shape of the brick
     * @return return the shape of the brick
     */
    public abstract Shape getBrickFace();

    /**
     * get the colour of the brick
     * @return return the colour of the brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * get the colour of the inner part of the brick
     * @return return the colour of the inner part of the brick
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * direction of the ball after the impact on the bricks
     * @param b ball
     * @return return the direction of the ball after hitting the brick
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /**
     * to check if the brick is broken or not
     * @return boolean value which true if the brick is broken and false if the brick is not broken
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * repair the brick back to the original condition
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * reduce the strength of the brick when an impact is occurred
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }
}
