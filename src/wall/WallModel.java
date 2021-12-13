package wall;

import brick.Brick;

public class WallModel {

    public static Brick[][] levels;
    public static int level;

    public static int brickCount;
    public static int ballCount;
    public static boolean ballLost;
    public static int playerScore;

    public static int getPlayerScore(){
        return playerScore;
    }

    public static void setPlayerScore(int num){
        playerScore=num;
    }

    public static void setBallLostTrue() {
        ballLost = true;
    }
    public static void setBallLostFalse() {
        ballLost = false;
    }

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    /**
     * check if there is extra playable levels
     * @return return true if there is still playable levels
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * check if the ball is lost
     * @return return true if the ball is lost
     */
    public static boolean isBallLost(){
        return ballLost;
    }

    /**
     * check if there is still extra ball
     * @return return true if there is not any ball
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /*
     * check if the brick of the level is all cleared
     * @return return true if all the bricks are destroyed
     */
    public boolean isDone(){
        return brickCount == 0;
    }
}
