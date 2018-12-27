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
        getChildren().add(conBrickController.getShape());

        setOnMouseMoved(e -> conBrickController.MouseMove(e));

        setOnKeyPressed(e -> conBrickController.KeyMove(e));

           addBall();
        //Thread t1 = new Thread(ballController2);
        Thread t = new Thread(conBrickController);

        //t1.start();
        t.start();


    }

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
            ballController.ballFade();
            getChildren().remove(ballControllers[num]);
//            try{
//                ballThread[num].sleep(100000000);
//                ballThread[num].interrupt();
//                ballThread[num].join();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            finally {
//                ballControllers[num] = null;
//                return;
//            }
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

                ballNum[num] = true;
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

    /**
     * 调用砖块碰撞判断
     * @param ball 球
     * @return
     */
    public int brickCatch(Ball ball){
        int flag =  CreateBrick.isBrick(ball, saveBrick, this);
        return flag;
    }

    /**
     *  界面删除砖块
     * @param flag i = flag / 10 j = flag % 10
     */
    public void deleteBrickShape( int flag){
        getChildren().remove(saveBrick[flag / 10][flag % 10]);
    }
    public void de(){
        for(int i = 0 ; i < 3 ; i ++){
            if(ballNum[i]){
                if(!ballControllers[i].isBallLive())
                  deleteBall(ballControllers[i]);
            }
        }
    }

    public ConBrickController getConBrickController() {
        return conBrickController;
    }


}
