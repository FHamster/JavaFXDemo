package sample;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.Controller.*;
import sample.GameObject.Ball;
import sample.GameObject.Brick;

public class GamePane extends Pane
{
    ConBrickController conBrickController;

    public GamePane()
    {
        Ball ball = new Ball(30, 30, 10, Color.FIREBRICK);
        Ball ball2 = new Ball(30, 70, 20, Color.CORNFLOWERBLUE);
        BallController ballController = new BallController(ball, this);
        BallController ballController2 = new BallController(ball2, this);

        Brick brick = new Brick(50, 50, 10, 50, Color.CORAL);
        BrickController brickController = new BrickController(Brick.getRandStdBrick(), this, 1);

        Brick brick2 = new Brick(50, 50, 50, 50, Color.CORAL);
        conBrickController = new ConBrickController(brick2, this, 1);

        conBrickController.getShape().requestFocus();

        getChildren().add(ballController.getShape());
        getChildren().add(ballController2.getShape());
        getChildren().add(brickController.getShape());
        getChildren().add(conBrickController.getShape());

        setOnKeyPressed(e->{
            switch (e.getCode())
            {
                case A:conBrickController.moveLeft();
                    break;
                case D:conBrickController.moveRight();
                    break;
            }
        });

        Thread t = new Thread(ballController);
        Thread t2 = new Thread(ballController2);
        Thread t3 = new Thread(brickController);
        Thread t4 = new Thread(conBrickController);
        t.start();
        t2.start();
        t3.start();
        t4.start();


    }

    public ConBrickController getConBrickController()
    {
        return conBrickController;
    }
}
