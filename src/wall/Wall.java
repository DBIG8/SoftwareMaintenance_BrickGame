package wall;

import ball.BallController;
import player.PlayerController;
import ball.Ball;
import brick.Brick;
import player.Player;
import ball.RubberBall;
import ball.BallModel;

import java.awt.*;
import java.awt.geom.Point2D;

/*
 * Wall class
 */
public class Wall {
    public static Rectangle area;

    public static Brick[] brick;
    public static Ball ball;
    public Player player;
    Levels levels = new Levels();

    public static Point startPoint;
    public static BallController ballController = new BallController();
    public static BallModel ballModel = new BallModel();
    public static PlayerController playerController = new PlayerController();

    /**
     * generate levels
     * @param drawArea playable area screen
     * @param brickCount number of brick
     * @param lineCount number of line
     * @param brickDimensionRatio size of brick
     * @param ballPos ball position
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        startPoint = new Point(ballPos);

        WallModel.levels = levels.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        WallModel.level = 0;

        WallModel.ballCount = 3;
        WallModel.ballLost = false;

        makeBall(ballPos);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;

        int speedX, speedY;
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

        ballController.setSpeed(speedX, speedY);
    }

    /**
     * Creates the ball.
     * @param ballPos ball position.
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }
}
