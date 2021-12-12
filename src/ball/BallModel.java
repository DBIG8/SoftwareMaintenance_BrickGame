package ball;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

public class BallModel {

    public static Shape ballFace;
    public static Point2D center;

    public static Color border;
    public static Color inner;

    public static int speedX;
    public static int speedY;

    /**
     * get method for ball's border colour
     * @return return the ball's border colour
     */
    public static Color getBorderColor(){
        return border;
    }

    /**
     * get method for ball's inner part colour
     * @return return the inner part colour of the ball
     */
    public static Color getInnerColor(){
        return inner;
    }

    /**
     * get method for the position of the ball
     * @return return the center position of the ball
     */
    public static Point2D getPosition(){
        return center;
    }

    /**
     * get method for the shape of the ball
     * @return the shape of the ball
     */
    public static Shape getBallFace(){
        return ballFace;
    }


    public static void setBallFace(RectangularShape tmp)
    {
        ballFace = tmp;
    }

    /**
     * get method for the X-axis (horizontal) speed of the ball
     * @return the horizontal speed
     */
    public static int getSpeedX(){
        return speedX;
    }

    /**
     * get method for the Y-axis (vertical) speed of the ball
     * @return the vertical speed
     */
    public static int getSpeedY(){
        return speedY;
    }

}
