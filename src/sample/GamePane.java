package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.Controller.BallController;
import sample.GameObject.ball.Ball;

public class GamePane extends Pane
{
    public GamePane()
    {
        BallController ballController = new BallController(new Ball(20, Color.GRAY), this);
        BallController ballController2 = new BallController(new Ball(10, Color.CORAL), this);
        getChildren().add(ballController.getShape());
        getChildren().add(ballController2.getShape());
        Thread t = new Thread(ballController);
        Thread t2 = new Thread(ballController2);
        t.start();
        try
        {
            Thread.sleep(2);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        t2.start();
    }

}
