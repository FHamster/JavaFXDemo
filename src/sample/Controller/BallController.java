package sample.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import sample.GameObject.ball.Ball;
import sample.GamePane;

public class BallController implements Runnable
{
    private Ball ball;
    private Pane pane;
    private Timeline animation;
    Circle circle;

    private double x;
    private double y;
    private double dx;
    private double dy;

    public BallController(Ball ball, GamePane pane)
    {
        this.ball = ball;
        this.pane = pane;
        this.animation = new Timeline(new KeyFrame(Duration.millis(10), e -> move()));
//        animation.setCycleCount(100);
        animation.setCycleCount(Animation.INDEFINITE);

        x = 30;
        y = 30;
        dx = 1;
        dy = 1;
        circle = new Circle(x, y, ball.getRidus(), ball.getColor());

    }

    public Circle getShape()
    {
        return circle;
    }

    public void move()
    {
        // Check boundaries
        if (x < ball.getRidus() || x > pane.getWidth() - ball.getRidus())
        {
            dx *= -1; // Change ball move direction
        } else if (y < ball.getRidus() || y > pane.getHeight() - ball.getRidus())
        {
            dy *= -1; // Change ball move direction
        }

        // Adjust ball position
        x += dx;
        y += dy;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    @Override
    public void run()
    {
        animation.play();
    }
}
