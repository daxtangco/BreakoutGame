import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {

    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 620;

    public static final int NBRICKS_PER_ROW = 10;
    private static final int NBRICK_ROWS = 10;

    public static final int BRICK_HEIGHT = 10;
    private static final int BRICK_SEP = 4;

    private static final int BRICK_WIDTH = (APPLICATION_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    public static final int PADDLE_WIDTH = 60;
    public static final int PADDLE_HEIGHT = 10;

    public static final int BALL_RADIUS = 10;

    private static final double BALL_VELOCITY_X = 2.0;
    private static final double BALL_VELOCITY_Y = 3.0;

    private static final int NTURNS = 3;

    private GRect paddle;
    private GOval ball;
    private double vx, vy;
    private int lives;
    private int score;
    private GLabel scoreLabel;
    private GLabel livesLabel;
    private GLabel gameOverLabel;

    public void init() {
        setSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
        addBricks();
        addPaddle();
        addBall();
        addScoreLabel();
        addLivesLabel();
        addMouseListeners();
    }

    private void addBricks() {
        Color[] colors = {Color.CYAN, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED};
        for (int row = 0; row < NBRICK_ROWS; row++) {
            for (int col = 0; col < NBRICKS_PER_ROW; col++) {
                double x = col * (BRICK_WIDTH + BRICK_SEP);
                double y = row * (BRICK_HEIGHT + BRICK_SEP);
                GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
                brick.setFilled(true);
                brick.setColor(colors[row / 2]);
                add(brick);
            }
        }
    }

    private void addPaddle() {
        double x = (APPLICATION_WIDTH - PADDLE_WIDTH) / 2;
        double y = APPLICATION_HEIGHT - PADDLE_HEIGHT - 30;
        paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setColor(Color.BLACK);
        add(paddle);
    }

    private void addBall() {
        double x = (APPLICATION_WIDTH - BALL_RADIUS * 2) / 2;
        double y = (APPLICATION_HEIGHT - BALL_RADIUS * 2) / 2;
        ball = new GOval(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
        add(ball);
    }

    private void addScoreLabel() {
        score = 0;
        scoreLabel = new GLabel("Score: " + score);
        scoreLabel.setFont("SansSerif-18");
        add(scoreLabel, 10, APPLICATION_HEIGHT - 10);
    }

    private void addLivesLabel() {
        lives = NTURNS;
        livesLabel = new GLabel("Lives: " + lives);
        livesLabel.setFont("SansSerif-18");
        add(livesLabel, APPLICATION_WIDTH - 100, APPLICATION_HEIGHT - 10);
    }

    public void mouseMoved(MouseEvent me) {
        double x = me.getX() - PADDLE_WIDTH / 2.0;
        if (x < 0) x = 0;
        if (x > getWidth() - PADDLE_WIDTH) x = getWidth() - PADDLE_WIDTH;
        paddle.setLocation(x, paddle.getY());
    }

    private GObject getCollidingObject(double x, double y) {
        GObject obj = getElementAt(x, y);
        if (obj != null && obj != scoreLabel && obj != livesLabel) {
            return obj;
        }
        obj = getElementAt(x + 2 * BALL_RADIUS, y);
        if (obj != null && obj != scoreLabel && obj != livesLabel) {
            return obj;
        }
        obj = getElementAt(x, y + 2 * BALL_RADIUS);
        if (obj != null && obj != scoreLabel && obj != livesLabel) {
            return obj;
        }
        obj = getElementAt(x + 2 * BALL_RADIUS, y + 2 * BALL_RADIUS);
        if (obj != null && obj != scoreLabel && obj != livesLabel) {
            return obj;
        }
        return null;
    }

    public void run() {
        vx = BALL_VELOCITY_X;
        vy = BALL_VELOCITY_Y;
        while (lives > 0) {
            ball.move(vx, vy);
            pause(10);
            if (ball.getX() <= 0 || ball.getX() >= getWidth() - BALL_RADIUS * 2) {
                vx = -vx;
            }
            if (ball.getY() <= 0) {
                vy = -vy;
            }
            if (ball.getY() >= getHeight() - BALL_RADIUS * 2) {
                lives--;
                livesLabel.setLabel("Lives: " + lives);
                if (lives > 0) {
                    resetBall();
                }
            }
            GObject collider = getCollidingObject(ball.getX(), ball.getY());
            if (collider != null) {
                if (collider == paddle) {
                    vy = -vy;
                } else {
                    remove(collider);
                    score++;
                    scoreLabel.setLabel("Score: " + score);
                    vy = -vy;
                }
            }
        }
        displayGameOver();
        waitForClick();
        restartGame();
    }

    private void resetBall() {
        ball.setLocation((APPLICATION_WIDTH - BALL_RADIUS * 2) / 2, (APPLICATION_HEIGHT - BALL_RADIUS * 2) / 2);
        pause(1000);
    }

    private void displayGameOver() {
        gameOverLabel = new GLabel("Game Over");
        gameOverLabel.setFont("SansSerif-36");
        add(gameOverLabel, (APPLICATION_WIDTH - gameOverLabel.getWidth()) / 2, (APPLICATION_HEIGHT - gameOverLabel.getHeight()) / 2);
    }

    private void restartGame() {
        removeAll();
        lives = NTURNS;
        score = 0;
        init();
        run();
    }

    public static void main(String[] args) {
        new Breakout().start(args);
    }
}
