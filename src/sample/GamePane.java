package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.Controller.*;
import sample.GameObjectView.Ball;
import sample.GameObjectView.Brick;


public class GamePane extends Pane {
    ConBrickController conBrickController;

    public GamePane() {
        setMinSize(600, 800);
        setMaxSize(600, 800);

        Image image = new Image(getClass().getResourceAsStream("../img/background.png"));
        setBackground(new Background(new BackgroundImage(image, null, null, null, null)));


//        Ball ball = new Ball(100, 600, 50, Color.FIREBRICK);
        Ball ball2 = new Ball(30, 730, 20, Color.CORNFLOWERBLUE);
        Ball ball3 = new Ball(300, 70, 20, Color.CORNFLOWERBLUE);
        Ball ball4 = new Ball(30, 700, 20, Color.CORNFLOWERBLUE);
//        BallController ballController = new BallController(ball, this);
        BallController ballController2 = new BallController(ball2, this);
        BallController ballController3 = new BallController(ball3, this);
        BallController ballController4 = new BallController(ball4, this);

        Brick brick = Brick.getRandStdBrick();
        BrickController brickController = new BrickController(Brick.getRandStdBrick(), this, 1);

        Brick brick2 = Brick.getConBrick();

        conBrickController = new ConBrickController(brick2, this);

//        getChildren().add(ballController.getShape());
        getChildren().add(ballController2.getShape());
        getChildren().add(ballController3.getShape());
        getChildren().add(ballController4.getShape());
        getChildren().add(brickController.getShape());
        getChildren().add(conBrickController.getShape());

        setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    conBrickController.moveLeft();
                    break;
                case D:
                    conBrickController.moveRight();
                    break;
            }
        });


//        Thread t = new Thread(ballController);
        Thread t2 = new Thread(ballController2);
        Thread t3 = new Thread(brickController);
        Thread t4 = new Thread(conBrickController);
        Thread t5 = new Thread(ballController3);
        Thread t6 = new Thread(ballController4);
//        t.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

    }

    public ConBrickController getConBrickController() {
        return conBrickController;
    }
}
