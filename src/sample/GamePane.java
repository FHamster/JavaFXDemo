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
import sample.myUtil.Discalculation;
import sample.myUtil.MyUtil;
import sample.myUtil.MyVector;

import java.util.ArrayList;
import java.util.List;


public class GamePane extends Pane {
    ConBrickController conBrickController;
    List<BrickController> list;
    public GamePane() {
        setMinSize(600, 800);
        setMaxSize(600, 800);

        Image image = new Image(getClass().getResourceAsStream("../img/background.png"));
        setBackground(new Background(new BackgroundImage(image, null, null, null, null)));


//        Ball ball = new Ball(100, 600, 50, Color.FIREBRICK);
        Ball ball2 = new Ball(30, 730, 20, Color.CORNFLOWERBLUE);
//        Ball ball3 = new Ball(300, 70, 20, Color.CORNFLOWERBLUE);
//        Ball ball4 = new Ball(30, 700, 20, Color.CORNFLOWERBLUE);
//        BallController ballController = new BallController(ball, this);
        BallController ballController2 = new BallController(ball2, this);
//        BallController ballController3 = new BallController(ball3, this);
//        BallController ballController4 = new BallController(ball4, this);

//        Brick brick = Brick.getRandStdBrick();
//        BrickController brickController = new BrickController(Brick.getRandStdBrick(), this, 1);

        Brick brick2 = Brick.getConBrick();

        conBrickController = new ConBrickController(brick2, this);

        list = createBrick();
        for(BrickController e : list){
            getChildren().add(e.getShape());
        }
//        getChildren().add(ballController.getShape());
        getChildren().add(ballController2.getShape());
//        getChildren().add(ballController3.getShape());
//        getChildren().add(ballController4.getShape());
//        getChildren().add(brickController.getShape());
        getChildren().add(conBrickController.getShape());

        setOnMouseMoved(e -> conBrickController.MouseMove(e));

        setOnKeyPressed(e -> conBrickController.KeyMove(e));


//        Thread t = new Thread(ballController);
        Thread t2 = new Thread(ballController2);
//        Thread t3 = new Thread(brickController);
        Thread t4 = new Thread(conBrickController);
//        Thread t5 = new Thread(ballController3);
//        Thread t6 = new Thread(ballController4);
//        t.start();
        t2.start();
//        t3.start();
        t4.start();
//        t5.start();
//        t6.start();

    }

    public List createBrick(){
        List<BrickController> list = new ArrayList<BrickController>();
        int dx,dy;
        dx = (int) Brick.getRandStdBrick().getX();
        dy = (int) Brick.getRandStdBrick().getY();
        int dwight = (int)Brick.getRandStdBrick().getWidth();
        int dhight = (int)Brick.getRandStdBrick().getHeight();

        for(int i = 0; i < 10; i++){
            int x = dx;
            for(int j = 0; j < 6; j ++){
                Brick brick = Brick.getRandStdBrick();
                brick.setX(x);
                brick.setY(dy);
                BrickController brickController = new BrickController(brick, this, 1);
                list.add(brickController);
                x += dwight;
            }
            dy += dhight;
        }

        return  list;
    }


    public int isBrick(Ball ball){

        int disController = -1;
        int flag = MyVector.myFlag(ball, list);
        if(flag < 0)
            return  disController;

        Brick brick = list.get(flag).getShape();

        if((ball.getCenterY() < brick.getX() || ball.getCenterY() > brick.getY() + brick.getHeight())){
            disController = 1;
        }else if((ball.getCenterX() < brick.getX() || ball.getCenterX() > brick.getX() + brick.getWidth())){
            disController = 2;
        }else if(ball.getCenterX() > brick.getX() && ball.getCenterX() < brick.getX() + brick.getWidth() &&
                ((ball.getCenterY() > brick.getY() && ball.getCenterY() < brick.getY() + brick.getHeight())
                        || brick.getY() + brick.getHeight() / 2 - ball.getCenterY() < brick.getHeight() / 2 + ball.getRadius()) ) {
            disController = 3;
        }

        MyVector.myDelete(list,flag);
        getChildren().remove(list.get(flag));
        return  disController;
    }

    public ConBrickController getConBrickController() {
        return conBrickController;
    }
}
