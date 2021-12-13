package ball;

import java.awt.*;
import java.awt.geom.Point2D;

/*
 * Ball class
 */
abstract public class Ball {

    public static Point2D up;
    public static Point2D down;
    public static Point2D left;
    public static Point2D right;

    /**
     * Ball Constructor handles the designs of the ball like appearance, speed and so on.
     * @param center initial location which is the center point of the ball
     * @param radiusA horizontal radius (width) of the ball
     * @param radiusB vertical radius (width) of the ball
     * @param inner colour for the inner part of the ball
     * @param border colour of the border of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        BallModel.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());

        BallModel.ballFace = makeBall(center,radiusA,radiusB);
        BallModel.border = border;
        BallModel.inner  = inner;
        BallModel.speedX = 0;
        BallModel.speedY = 0;
    }

    /**
     * An abstract method to make a rubber ball
     * @param center initial location which is the center point of the ball
     * @param radiusA horizontal radius (width) of the ball
     * @param radiusB vertical radius (width) of the ball
     * @return return the ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

}
