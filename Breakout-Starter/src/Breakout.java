import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {

    // Width and height of application window in pixels
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 620;

    // Number of bricks per row
    public static final int NBRICKS_PER_ROW = 10;

    // Number of rows of bricks
    private static final int NBRICK_ROWS = 10;

    // Height of a brick
    public static final int BRICK_HEIGHT = 10;

    // Separation between bricks
    private static final int BRICK_SEP = 4;

    // Width of a brick
    private static final int BRICK_WIDTH
            = (APPLICATION_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    // Dimensions of the paddle
    public static final int PADDLE_WIDTH = 60;
    public static final int PADDLE_HEIGHT = 10;

    // Radius of the ball in pixels
    public static final int BALL_RADIUS = 10;

    // You could add more variables in this file

    /**
     * the init() function is in charge of setting up the graphics at the start of the program
     */
    public void init() {
        setSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));

        //TODO: Write your brick initialization, paddle initialization, and ball initialization
        //TODO: in the init() function

        addMouseListeners();
    }


    /**
     * the mouseMoved() function is in-charge of events caused by the mouse moving.
     * @param me: variable for MouseEvent - do not change
     */
    public void mouseMoved(MouseEvent me) {
        //TODO: Write the mouse handling events in this function
        //TODO: HINT: to get the current position of the mouse, use me.getX() and me.getY().
    }

    /**
     * this function returns the object that the ball collided with
     * @param x: x coordinate in the canvas
     * @param y: y coordinate in the canvas
     */
    private GObject getCollidingObject(double x, double y) {
        //TODO: HINT: this should return the object that the ball collided with. Use the function getElementAt(x, y) to
        //TODO: to check the element where the ball collided.
        return null;
    }

    /**
     * This function handles the logic of the program when the game has started.
     */
    public void run() {
        //TODO: Write the run code here
    }

    public static void main(String[] args) {
        new Breakout().start(args);
    }
}