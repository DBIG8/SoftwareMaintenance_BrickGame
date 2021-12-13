package brick;

import java.awt.*;

/*
 * CLayBrick class
 */
public class ClayBrick extends Brick {
    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(139,69,19).darker();
    private static final Color DEF_BORDER = new Color(255,228,181);
    private static final int CLAY_STRENGTH = 1;

    /**
     * generate clay brick
     * @param point position of the brick
     * @param size size of the brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
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
        return super.brickFace;
    }

}
