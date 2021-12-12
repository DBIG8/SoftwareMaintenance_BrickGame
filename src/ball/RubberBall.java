package ball;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Rubber Ball Class
 */
public class RubberBall extends Ball{

    //ball features
    private static final int DEF_RADIUS = 15;
    private static final Color DEF_INNER_COLOR = new Color(153,50,204);

    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();

    /**
     * generate rubber ball
     * @param center center of the ball
     */
    public RubberBall(Point2D center){
        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }

    /**
     * make the ball
     * @param center  initial location which is the center point of the ball
     * @param radiusA horizontal radius (width) of the ball
     * @param radiusB vertical radius (width) of the ball
     * @return return the rubber ball
     */
    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {

        double x = center.getX() - (radiusA / 2);
        double y = center.getY() - (radiusB / 2);

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}
