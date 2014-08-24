/*
 * File: Breakout.java
 * -------------------
 * A clone of the famous Breakout game.
 *
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {
  private static final int WIDTH = 400;
  private static final int HEIGHT = 600;
  private static final int PADDLE_WIDTH = 60;
  private static final int PADDLE_HEIGHT = 10;
  private static final int PADDLE_Y_OFFSET = 30;
  private static final int BRICKS_PER_ROW = 10;
  private static final int BRICK_ROWS = 10;
  private static final int BRICK_SEP = 4;
  private static final int BRICK_WIDTH =
    WIDTH / BRICKS_PER_ROW - BRICK_SEP;
  private static final int BRICK_HEIGHT = 8;
  private static final int BALL_DIAMETER = 10;
  private static final int BRICK_Y_OFFSET = 70;
  private static final int TURNS = 3;
  private static final int DELAY = 30;

  public static void main(String[] args) {
    String[] sizeArgs = { "width=" + WIDTH, "height=" + HEIGHT };
    new Breakout().start(sizeArgs);
  }

  public void init() {
    drawBricks();
    drawPaddle();
    drawBall();

    mouseXLabel = new GLabel("");
    mouseXLabel.setFont("Times New Roman-12");
    add(mouseXLabel, WIDTH - 25, 15);

    addMouseListeners();
    addKeyListeners();
  }

  public void run() {
    int turnsLeft = TURNS;
    vx = rgen.nextDouble(1.0, 3.0);
    vy = vx;
    if (rgen.nextBoolean(0.5)) vx = -vx;
    while(turnsLeft > 0) {
      ball.move(vx, vy);
      checkForCollision();
      pause(DELAY);
    }
  }

  private void drawBricks() {
    Color[] pallate = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN };
    int x;
    int y = BRICK_Y_OFFSET;

    for(int j = 0; j < BRICK_ROWS; j++) {
      x = BRICK_SEP / 2;

      for(int i = 0; i < BRICKS_PER_ROW; i++) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(pallate[j / 2]);
        add(brick);

        x += BRICK_SEP + BRICK_WIDTH;
      }

      y += BRICK_SEP + BRICK_HEIGHT;
    }
  }

  private void drawPaddle() {
    int x = WIDTH / 2 - PADDLE_WIDTH / 2;
    int y = HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET;
    paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    paddle.setFilled(true);
    add(paddle);
  }

  private void drawBall() {
    int x = WIDTH / 2;
    int y = HEIGHT / 2;
    ball = new GOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
    ball.setFilled(true);
    add(ball);
  }

  public void mouseMoved(MouseEvent e) {
    int mouseX = e.getX();
    mouseXLabel.setLabel(Integer.toString(mouseX));

    double x = mouseX - PADDLE_WIDTH / 2;
    double y = paddle.getY();

    x = x < 0 ? 0 : x;
    x = x + PADDLE_WIDTH > WIDTH ? WIDTH - PADDLE_WIDTH : x;
    paddle.setLocation(x, y);
  }

  private void checkForCollision() {
    if (ball.getX() < 0 || ball.getX() > WIDTH - BALL_DIAMETER)
      vx = -vx;
    if (ball.getY() < 0 || ball.getY() > HEIGHT - BALL_DIAMETER)
      vy = -vy;
  }

  /* Private instance variables */
  private GLabel mouseXLabel;
  private GRect paddle;
  private GOval ball;
  private double vx, vy;
  private RandomGenerator rgen = new RandomGenerator();
}
