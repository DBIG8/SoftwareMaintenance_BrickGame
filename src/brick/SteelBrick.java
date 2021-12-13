package brick;

import gameboard.GameBoard;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/*
 * Wall class
 */
public class SteelBrick extends Brick {
    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(105,105,105);
    private static final Color DEF_BORDER = new Color(0,0,0);
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }

    /**
     * generate the size of the clay brick
     * @param pos  position of the brick
     * @param size size of the brick
     * @return return the clay brick structure
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * get the shape of the clay brick
     * @return return the shape of the clay brick
     */
    @Override
    public Shape getBrickFace() {
        return brickFace;
    }

    /**
     * detect the impact of the brick
     * @param point point of the impact occurred
     * @param dir   direction of the impact occurred
     * @return return boolean value which true if brick is broken, false if brick is not broken
     */
    public boolean setImpact(Point2D point, int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    /**
     * detect the impact of the ball
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }
}
