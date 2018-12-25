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
import java.util.Vector;


public class GamePane extends Pane {
    ConBrickController conBrickController;
    List<BrickController> list;
    BrickController[][] saveBrick;
    public GamePane() {
        setMinSize(600, 800);
        setMaxSize(600, 800);

        Image image = new Image(getClass().getResourceAsStream("../img/background.png"));
        setBackground(new Background(new BackgroundImage(image, null, null, null, null)));


        Ball ball2 = new Ball(30, 730, 20, Color.CORNFLOWERBLUE);

        BallController ballController2 = new BallController(ball2, this);

        Brick brick2 = Brick.getConBrick();

        conBrickController = new ConBrickController(brick2, this);

        saveBrick = createBrick();
        for(int i = 0; i < 6; i ++){
            for(int j = 0; j < 6; j ++){
                getChildren().add(saveBrick[i][j].getShape());
            }
        }
//        list = createBrickList();
//        for(BrickController e : list){
//            getChildren().add(e.getShape());
//        }
        getChildren().add(ballController2.getShape());
        getChildren().add(conBrickController.getShape());

        setOnMouseMoved(e -> conBrickController.MouseMove(e));

        setOnKeyPressed(e -> conBrickController.KeyMove(e));


        Thread t2 = new Thread(ballController2);;
        Thread t4 = new Thread(conBrickController);
        t2.start();
        t4.start();

    }

    public List createBrickList(){
        List<BrickController> list = new Vector<BrickController>();
        int dx,dy;
        dx = (int) Brick.getRandStdBrick().getX();
        dy = (int) Brick.getRandStdBrick().getY();
        int dwight = (int)Brick.getRandStdBrick().getWidth();
        int dhight = (int)Brick.getRandStdBrick().getHeight();

        for(int i = 0; i < 2; i++){
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

    public BrickController[][] createBrick(){
        BrickController[][]  save = new BrickController[6][6];
        int dx,dy;
        dx = (int) Brick.getRandStdBrick().getX();
        dy = (int) Brick.getRandStdBrick().getY();
        int dwight = (int)Brick.getRandStdBrick().getWidth();
        int dhight = (int)Brick.getRandStdBrick().getHeight();

        for(int i = 0; i < 6; i++){
            int x = dx;
            for(int j = 0; j < 6; j ++){
                Brick brick = Brick.getRandStdBrick();
                brick.setX(x);
                brick.setY(dy);
                save[i][j] = new BrickController(brick, this, 1);
                x += dwight;
            }
            dy += dhight;
        }
        return save;
    }

    public int isBrick(Ball ball){

        int disController = -1;
//        int flag = MyVector.myFlag(ball, list);
        int flag = MyVector.myFlag(ball,saveBrick);

        if(flag < 0)
            return  disController;

        Brick brick = saveBrick[flag / 10][flag % 10].getShape();
//        Brick brick = list.get(flag).getShape();

        if((ball.getCenterY() < brick.getX() || ball.getCenterY() > brick.getY() + brick.getHeight())){
            disController = 1;
        }else if((ball.getCenterX() < brick.getX() || ball.getCenterX() > brick.getX() + brick.getWidth())){
            disController = 2;
        }else if(ball.getCenterX() > brick.getX() && ball.getCenterX() < brick.getX() + brick.getWidth() &&
                ((ball.getCenterY() > brick.getY() && ball.getCenterY() < brick.getY() + brick.getHeight())
                        || brick.getY() + brick.getHeight() / 2 - ball.getCenterY() < brick.getHeight() / 2 + ball.getRadius()) ) {
            disController = 3;
        }

//        MyVector.myDelete(list,flag);
//        getChildren().remove(list.get(flag));
        MyVector.myDelete(saveBrick,flag);
        getChildren().remove(saveBrick[flag / 10][flag % 10]);

        return  disController;
    }

    public ConBrickController getConBrickController() {
        return conBrickController;
    }
}
