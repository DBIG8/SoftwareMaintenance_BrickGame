package brick;

import gameboard.GameBoard;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/*
 * CementBrick class
 */
public class CementBrick extends Brick {
    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(169,169,169);
    private static final Color DEF_BORDER = new Color(255,228,181);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;

    /**
     * generate cement brick
     * @param point position of the brick
     * @param size size oof the brick
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.brickFace;
    }

    /**
     * generate the size of the cement brick
     * @param pos  position of the brick
     * @param size size of the brick
     * @return return the cement brick structure
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * detect the impact of the brick
     * @param point point of the impact occurred
     * @param dir   direction of the impact occurred
     * @return return boolean value which true if brick is broken, false if brick is not broken
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir,this);
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * get the shape of the brick
     * @return return the shape of the cement brick
     */
    @Override
    public Shape getBrickFace() {
        return brickFace;
    }

    /**
     * update the crack condition of the brick
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    /**
     * repair the brick back to the original condition
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
