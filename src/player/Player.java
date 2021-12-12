package player;

import java.awt.*;

/**
 * Player class
 */
public class Player {

    public static final Color BORDER_COLOR = new Color(0, 0, 0);
    public static final Color INNER_COLOR = new Color(255, 255, 255);

    public static Rectangle playerFace;
    public static Point ballPoint;

    /**
     * generate player bar
     * @param ballPoint position of the ball
     * @param width width of player bar
     * @param height height of player bar
     * @param container shape of player bar
     */
    public Player(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        PlayerModel.moveAmount = 0;
        playerFace = makeRectangle(width, height);
        PlayerModel.min = container.x + (width / 2);
        PlayerModel.max = PlayerModel.min + container.width - width;
    }

    /**
     * Make the shape of player bar
     * @param width width of player bar
     * @param height height of player bar
     * @return return the shape of player bar
     */
    private Rectangle makeRectangle(int width, int height) {
        Point p = new Point((int) (ballPoint.getX() - (width / 2)), (int) ballPoint.getY());
        return new Rectangle(p, new Dimension(width, height));
    }

    /**
     * stop player bar from moving
     */
    public static void stop() {
        PlayerModel.moveAmount = 0;
    }

    public Shape getPlayerFace() {
        return playerFace;
    }
}








