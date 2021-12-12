package player;

import ball.BallModel;
import ball.Ball;

import java.awt.*;

public class PlayerController {

    /**
     * move ball and player bar
     * @param p point
     */
    public void moveTo(Point p) {
        Player.ballPoint.setLocation(p);
        Player.playerFace.setLocation(Player.ballPoint.x - (int) Player.playerFace.getWidth() / 2, Player.ballPoint.y);
    }


    /**
     * move the player bar along x-axis
     */
    public void move(){
        double x = Player.ballPoint.getX() + PlayerModel.moveAmount;
        if(x < PlayerModel.min || x > PlayerModel.max)
            return;
        Player.ballPoint.setLocation(x,Player.ballPoint.getY());
        Player.playerFace.setLocation(Player.ballPoint.x - (int)Player.playerFace.getWidth()/2,Player.ballPoint.y);
    }


    /**
     * move player bar to the direction of left
     */
    public static void moveLeft(){
        PlayerModel.setPlayerMoveLeft();
    }

    /**
     * move player bar to the direction of right
     */
    public static void moveRight(){
        PlayerModel.setPlayerMoveRight();
    }

    /**
     * check the impact of ball and player bar
     * @param b ball
     * @return return true if ball impacts player bar
     */
    public boolean impact(Ball b) {
        return Player.playerFace.contains(BallModel.getPosition()) && Player.playerFace.contains(Ball.down);
    }

}
