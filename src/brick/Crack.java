package brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Crack class that implement the crack on the bricks when the ball hit the bricks
 */
public class Crack {

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;
    private static Random rnd;

    private GeneralPath crack;

    private int crackDepth;
    private int steps;


    /**
     * Creates crack, set random path for the crack, depth of crack and crack steps
     * @param crackDepth depth of crack
     * @param steps number of steps to generate the path for crack
     */
    public Crack(int crackDepth, int steps) {
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
    }

    public GeneralPath draw() {

        return crack;
    }

    /**
     * reset the crack on the bricks
     */
    public void reset() {
        crack.reset();
    }

    /**
     * set the starting and ending point of the crack
     * @param point the starting point of the crack
     * @param direction the direction of the crack
     */
    protected void makeCrack(Point2D point, int direction, Brick b) {
        Rectangle bounds = b.brickFace.getBounds();

        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();

        switch (direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);

                break;
        }
    }

    /**
     * generate a crack from the starting and ending point of the crack
     * @param start starting point of the crack
     * @param end ending point of the crack
     */
    protected void makeCrack(Point start, Point end) {

        GeneralPath path = new GeneralPath();

        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) steps;
        double h = (end.y - start.y) / (double) steps;

        int bound = crackDepth;
        int jump = bound * 5;

        double x, y;

        for (int i = 1; i < steps; i++) {

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump, JUMP_PROBABILITY);

            path.lineTo(x, y);
        }

        path.lineTo(end.x, end.y);
        crack.append(path, true);
    }

    /**
     * generate a random integer
     * @param bound boundary of random integers
     * @return return the random generated integer
     */
    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound;
    }

    /**
     * check the middle position of the crack
     * @param i the step that the generated crack is
     * @param steps number of steps to generate the path for crack
     * @param divisions number of segments that the crack to generate
     * @return return the step
     */
    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }

    /**
     * @param bound the boundary of random integers
     * @param probability probability of crack skipping steps
     * @return return a random number which is within the boundary
     */
    private int jumps(int bound, double probability) {

        if (rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return 0;
    }

    /**
     * generate a crack point randomly
     * @param from starting point fo the crack
     * @param to ending point of the crack
     * @param direction direction of the crack
     * @return return the randomly generated point
     */
    private Point makeRandomPoint(Point from, Point to, int direction) {

        Point out = new Point();
        int pos;

        switch (direction) {
            case HORIZONTAL:
                pos = rnd.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
                break;
            case VERTICAL:
                pos = rnd.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
                break;
        }
        return out;
    }
}