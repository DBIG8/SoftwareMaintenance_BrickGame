package ball;

import java.awt.*;
import java.awt.geom.RectangularShape;

/*
 * BallController class
 */
public class BallController {

    /**
     * control and show the movement of the ball
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) BallModel.ballFace;
        BallModel.center.setLocation((BallModel.center.getX() + BallModel.speedX),(BallModel.center.getY() + BallModel.speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((BallModel.center.getX() -(w / 2)),(BallModel.center.getY() - (h / 2)),w,h);
        this.setPoints(w,h);

        BallModel.setBallFace(tmp);
    }

    /**
     * move the ball to the specific location
     * @param p coordinate of the ball
     */
    public void moveTo(Point p){
        BallModel.center.setLocation(p);

        RectangularShape tmp = (RectangularShape) BallModel.ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((BallModel.center.getX() -(w / 2)),(BallModel.center.getY() - (h / 2)),w,h);
        BallModel.ballFace = tmp;
    }

    /**
     * set the points of coordinate X and Y
     * @param width width coordinate X of the ball
     * @param height width coordinate Y of the ball
     */
    private void setPoints(double width,double height){
        Ball.up.setLocation(BallModel.center.getX(),BallModel.center.getY()-(height / 2));
        Ball.down.setLocation(BallModel.center.getX(),BallModel.center.getY()+(height / 2));

        Ball.left.setLocation(BallModel.center.getX()-(width / 2),BallModel.center.getY());
        Ball.right.setLocation(BallModel.center.getX()+(width / 2),BallModel.center.getY());
    }

    /**
     * set the horizontal speed of the ball
     * @param s speed of the ball in X-axis (horizontal) direction
     */
    public void setXSpeed(int s){
        BallModel.speedX = s;
    }

    /**
     * set the vertical speed of the ball
     * @param s speed of the ball in Y-axis (vertical) direction
     */
    public void setYSpeed(int s){
        BallModel.speedY = s;
    }

    /**
     * Reverse the moving speed and direction of the ball along X-axis
     */
    public void reverseX(){
        BallModel.speedX *= -1;
    }

    /**
     * Reverse the moving speed and direction of the ball along Y-axis
     */
    public void reverseY(){
        BallModel.speedY *= -1;
    }

    public void setSpeed(int x,int y){
        BallModel.speedX = x;
        BallModel.speedY = y;
    }
}
