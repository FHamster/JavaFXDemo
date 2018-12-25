package sample.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import sample.GameObjectView.Ball;
import sample.GameObjectView.Brick;
import sample.GamePane;

import java.awt.*;

public class BallController extends AbstractController
{
    private  Ball ball;

    private double dx;
    private double dy;

    public BallController(Ball ball, GamePane pane)
    {
        super(pane, null);
        super.setAnimation(new Timeline(new KeyFrame(Duration.millis(5), e -> move())));
        this.ball = ball;

        getAnimation().setCycleCount(Animation.INDEFINITE);

        dx = 1;
        dy = 1;
    }


    public Ball getShape()
    {
        return ball;
    }

    private boolean isBound()
    {
        double x = ball.getCenterX();
        double y = ball.getCenterY();
        double radius = ball.getRadius();
        Pane pane = this.getPane();


        boolean flag = false;
        // Check boundaries
        if (x < radius || x > pane.getWidth() - radius)
        {
            dx *= -1; // Change ball move direction
            flag = true;
        } else if (y < radius || y > pane.getHeight() - radius)
        {
            dy *= -1; // Change ball move direction
            flag = true;
        }

        return flag;
    }

    boolean isBric (){
        double x = ball.getCenterX();
        double y = ball.getCenterY();
        double radius = ball.getRadius();
        Pane pane = this.getPane();


        boolean flag = false;
        // Check boundaries
        if (Discalculation.disVC(ball, getPane().getConBrickController().getShape()) <= ball.getRadius())
        {
            dx *= -1;
            dy *= -1;

            flag = true;
        }

        return flag;
    }
    public void move()
    {
        isBound();
        isBric();

        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);
    }

    @Override
    public void run()
    {
        getAnimation().play();
    }
}
