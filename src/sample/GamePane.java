package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.Controller.*;
import sample.GameObjectView.Ball;
import sample.GameObjectView.Brick;
import sample.myUtil.CreateBrick;
import sample.myUtil.CreateBrickPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GamePane extends Pane {
    ConBrickController conBrickController;
//    List<BrickController> list;
    boolean[] ballNum = new boolean[5];
    BrickController[][] saveBrick;
    Thread[] ballThread = new Thread[5];
    BallController[] ballControllers = new BallController[5];
    public GamePane() {
        setMinSize(600, 800);
        setMaxSize(600, 800);


        Image image = new Image(getClass().getResourceAsStream("../img/background.png"));
        setBackground(new Background(new BackgroundImage(image, null, null, null, null)));


//        Ball ball2 = new Ball(30, 730, 20, Color.CORNFLOWERBLUE);
//
//        BallController ballController2 = new BallController(ball2, this, 0);

        Brick brick2 = Brick.getConBrick();

        conBrickController = new ConBrickController(brick2, this);

        saveBrick = CreateBrick.createBrick(this);
        for(int i = 0; i < 6; i ++){
            for(int j = 0; j < 6; j ++){
                getChildren().add(saveBrick[i][j].getShape());
            }
        }
//        list = createBrickList();
//        for(BrickController e : list){
//            getChildren().add(e.getShape());
//        }
        //getChildren().add(ballController2.getShape());
        getChildren().add(conBrickController.getShape());

        setOnMouseMoved(e -> conBrickController.MouseMove(e));

        setOnKeyPressed(e -> conBrickController.KeyMove(e));

           addBall();
        //Thread t1 = new Thread(ballController2);
        Thread t2 = new Thread(conBrickController);

        //t1.start();
        t2.start();

    }

//    public List createBrickList() {
//
//        List<BrickController> list = new Vector<BrickController>();
//        int dx, dy;
//        dx = (int) Brick.getRandStdBrick().getX();
//        dy = (int) Brick.getRandStdBrick().getY();
//        int dwight = (int) Brick.getRandStdBrick().getWidth();
//        int dhight = (int) Brick.getRandStdBrick().getHeight();
//
//        for (int i = 0; i < 2; i++) {
//            int x = dx;
//            for (int j = 0; j < 6; j++) {
//                Brick brick = Brick.getRandStdBrick();
//                brick.setX(x);
//                brick.setY(dy);
//                BrickController brickController = new BrickController(brick, this, 1);
//                list.add(brickController);
//                x += dwight;
//            }
//            dy += dhight;
//        }
//
//        return list;
//    }
/*
    public Brick SetBrickLo(int i, int j) {
    }*/

    //图形添加
    public void addShape(){
        int i = 0;
        for(int j = 0; j < 6; j ++){
            getChildren().add(saveBrick[i][j].getShape());
        }
    }

    public void deleteBall(BallController ballController){
        try{
            int num = ballController.getNum();
            ballNum[num] = false;
            System.out.println("当前线程stop:" + ballThread[num].currentThread());
            getChildren().remove(ballControllers[num]);
            ballControllers[num] = null;
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    public boolean addBall(){
        boolean flag = false;
        Brick conBrick = this.conBrickController.getShape();
        for(int i = 0; i < 3; i++){
            if(!ballNum[i]){
                int num = i;
                Ball ball = new Ball( (conBrick.getX() + Math.random() * conBrick.getWidth()),
                        conBrick.getY(),20, Color.CORNFLOWERBLUE);
                ballControllers[num] = new BallController(ball ,this , num);
                double dx,dy;
                dx = Math.random() * 0.6;
                dy = 0.6 - dx;
                ballControllers[num].setDx(dx);
                ballControllers[num].setDy(dy);
                getChildren().add(ballControllers[num].getShape());
                ballThread[num] = new Thread(ballControllers[num]);
                ballThread[num].start();
                flag = true;
                break;
            }
        }

        return flag;
    }

    public int isBrick(Ball ball){

        int disController = -1;
//        int flag = CreateBrickPane.myFlag(ball, list);
        int flag = CreateBrickPane.myFlag(ball,saveBrick);

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

//        CreateBrickPane.myDelete(list,flag);
//        getChildren().remove(list.get(flag));
        CreateBrickPane.myDelete(saveBrick,flag);
        getChildren().remove(saveBrick[flag / 10][flag % 10]);

        return  disController;
    }


    public ConBrickController getConBrickController() {
        return conBrickController;
    }
}
