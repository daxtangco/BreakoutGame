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

    // Paddle Y position
    private static final int PADDLE_Y_OFFSET = 30;

    // Animation delay or pause time between ball moves
    private static final int DELAY = 10;

    private GRect paddle;
    private GOval ball;
    private double vx, vy;

    // Number of turns
    private static final int NTURNS = 3;

    private int score;
    private GLabel scoreLabel;
    private int lives;
    private GLabel livesLabel;

    public void init() {
        setSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
        setupBricks();
        setupPaddle();
        setupBall();
        setupScoreboard();
        addMouseListeners();
    }

    private void setupBricks() {
        for (int row = 0; row < NBRICK_ROWS; row++) {
            for (int col = 0; col < NBRICKS_PER_ROW; col++) {
                double x = col * (BRICK_WIDTH + BRICK_SEP);
                double y = row * (BRICK_HEIGHT + BRICK_SEP);
                GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
                brick.setFilled(true);
                brick.setColor(getBrickColor(row));
                add(brick);
            }
        }
    }

    private Color getBrickColor(int row) {
        switch (row) {
            case 0: case 1: return Color.RED;
            case 2: case 3: return Color.ORANGE;
            case 4: case 5: return Color.YELLOW;
            case 6: case 7: return Color.GREEN;
            case 8: case 9: return Color.CYAN;
            default: return Color.BLACK;
        }
    }

    private void setupPaddle() {
        double x = (APPLICATION_WIDTH - PADDLE_WIDTH) / 2;
        double y = APPLICATION_HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
        paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle);
    }

    private void setupBall() {
        double x = (APPLICATION_WIDTH - BALL_RADIUS * 2) / 2;
        double y = (APPLICATION_HEIGHT - BALL_RADIUS * 2) / 2;
        ball = new GOval(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        add(ball);

        // Set the initial velocity of the ball
        vx = 3.0;
        vy = 3.0;
    }

    private void setupScoreboard() {
        score = 0;
        scoreLabel = new GLabel("Score: " + score);
        scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        scoreLabel.setLocation(10, APPLICATION_HEIGHT - 10);
        add(scoreLabel);

        lives = NTURNS;
        livesLabel = new GLabel("Lives: " + lives);
        livesLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        livesLabel.setLocation(APPLICATION_WIDTH - 80, APPLICATION_HEIGHT - 10);
        add(livesLabel);
    }

    public void mouseMoved(MouseEvent me) {
        double x = me.getX() - PADDLE_WIDTH / 2;
        if (x >= 0 && x <= APPLICATION_WIDTH - PADDLE_WIDTH) {
            paddle.setLocation(x, paddle.getY());
        }
    }

    private GObject getCollidingObject(double x, double y) {
        GObject collider = getElementAt(x, y);
        if (collider != null) {
            return collider;
        }
        collider = getElementAt(x + 2 * BALL_RADIUS, y);
        if (collider != null) {
            return collider;
        }
        collider = getElementAt(x, y + 2 * BALL_RADIUS);
        if (collider != null) {
            return collider;
        }
        collider = getElementAt(x + 2 * BALL_RADIUS, y + 2 * BALL_RADIUS);
        if (collider != null) {
            return collider;
        }
        return null;
    }

    public void run() {
        waitForClick();
        while (lives > 0) {
            playGame();
            if (ball.getY() >= getHeight()) {
                lives--;
                updateLives();
                if (lives > 0) {
                    remove(ball);
                    setupBall();
                    waitForClick();
                }
            }
        }
        displayGameOver();
    }

    private void playGame() {
        while (true) {
            ball.move(vx, vy);
            pause(DELAY);
            if (ball.getX() <= 0 || ball.getX() >= getWidth() - BALL_RADIUS * 2) {
                vx = -vx;
            }
            if (ball.getY() <= 0) {
                vy = -vy;
            }
            if (ball.getY() >= getHeight() - BALL_RADIUS * 2) {
                break;
            }
            GObject collider = getCollidingObject(ball.getX(), ball.getY());
            if (collider == paddle) {
                vy = -vy;
            } else if (collider != null) {
                remove(collider);
                score++;
                updateScore();
                vy = -vy;
            }
        }
    }

    private void updateScore() {
        scoreLabel.setLabel("Score: " + score);
    }

    private void updateLives() {
        livesLabel.setLabel("Lives: " + lives);
    }

    private void displayGameOver() {
        GLabel gameOverLabel = new GLabel("Game Over");
        gameOverLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        gameOverLabel.setLocation((getWidth() - gameOverLabel.getWidth()) / 2, getHeight() / 2);
        add(gameOverLabel);
    }

    public static void main(String[] args) {
        new Breakout().start(args);
    }
}
