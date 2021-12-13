package wall;

import brick.Brick;
import ball.Ball;
import ball.BallModel;
import brick.Crack;
import gameboard.GameBoard;

import java.io.IOException;
import java.awt.geom.Point2D;

public class WallController {

    /**
     * set the x-coordinate speed of the ball.
     * @param s x-coordinate speed of the ball
     */
    public void setBallXSpeed(int s){
        Wall.ballController.setXSpeed(s);
    }

    /**
     * set the y-coordinate speed of the ball.
     * @param s y-coordinate speed of the ball
     */
    public void setBallYSpeed(int s){
        Wall.ballController.setYSpeed(s);
    }

    /**
     * reset number of ball
     */
    public void resetBallCount(){
        WallModel.ballCount = 3;
    }

    /**
     * go to next level
     */
    public void nextLevel(){
        Wall.brick = WallModel.levels[WallModel.level++];
        WallModel.brickCount = Wall.brick.length;

        if (WallModel.level == 5 || WallModel.level == 6){
            int speedX,speedY;
            do{
                speedX = 4;
            }while(speedX == 0);
            do{
                speedY = -4;
            }while(speedY == 0);

            Wall.ballController.setSpeed(speedX,speedY);
        }
    }

    public void wallReset(){
        for(Brick b : Wall.brick)
            b.repair();
        WallModel.brickCount = Wall.brick.length;
        WallModel.ballCount = 3;
    }

    /**
     * check the impact between the wall and ball
     * @return return true if there is impact occurred
     */
    public boolean impactWall(){
        for(Brick b : Wall.brick){
            switch(b.findImpact(Wall.ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    Wall.ballController.reverseY();
                    WallModel.playerScore += 100; //not sure is work or not,need check the score got increase ma.
                    return b.setImpact(Ball.down, Crack.UP);
                case Brick.DOWN_IMPACT:
                    Wall.ballController.reverseY();
                    WallModel.playerScore += 100;
                    return b.setImpact(Ball.up, Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    Wall.ballController.reverseX();
                    WallModel.playerScore += 100;
                    return b.setImpact(Ball.right, Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    Wall.ballController.reverseX();
                    WallModel.playerScore += 100;
                    return b.setImpact(Ball.left, Crack.LEFT);
            }
        }
        return false;
    }

    public void move(){
        Wall.playerController.move();
        Wall.ballController.move();
    }

    /**
     * check impact between everything like ball and wall, ball and brick and etc
     */
    public void findImpacts(){
        if(Wall.playerController.impact(Wall.ball)){
            Wall.ballController.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            WallModel.brickCount--;
        }
        else if(impactBorder()) {
            Wall.ballController.reverseX();
        }
        else if(BallModel.getPosition().getY() < Wall.area.getY()){
            Wall.ballController.reverseY();
        }
        else if(BallModel.getPosition().getY() > Wall.area.getY() + Wall.area.getHeight()){
            WallModel.ballCount--;
            WallModel.setBallLostTrue();
        }
    }

    /**
     * check impact between ball and borders
     * @return return true if ball hit the borders
     */
    private boolean impactBorder(){
        Point2D p = BallModel.getPosition();
        return ((p.getX() < Wall.area.getX()) ||(p.getX() > (Wall.area.getX() + Wall.area.getWidth())));
    }

    public static void ballReset(){
        Wall.playerController.moveTo(Wall.startPoint);
        Wall.ballController.moveTo(Wall.startPoint);
        int speedX,speedY;

        if(WallModel.level > 4)
        {
            speedX = 5;
            speedY = -5;
        }
        else{
            do{
                speedX = 2;
            }while(speedX == 0);
            do{
                speedY = -2;
            }while(speedY == 0);
        }


        Wall.ballController.setSpeed(speedX,speedY);
        WallModel.setBallLostFalse();
    }

    /*
     * reset the level.
     */
    public void resetLevels() {
        Wall.brick = WallModel.levels[0];
        WallModel.level=0;
        WallModel.brickCount = Wall.brick.length;
    }
}
