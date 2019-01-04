package sample;


import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.controller.*;
import sample.gameObjectView.Ball;
import sample.gameObjectView.Brick;
import sample.infoPane.controller.TimerController;
import sample.myUtil.CreateBrick;
import sample.myUtil.CreateProps;

import javax.lang.model.element.NestingKind;


public class GamePane extends Pane
{
    ConBrickController conBrickController;
    //    List<BrickController> list;
    boolean[] ballNum = new boolean[5];
    BrickController[][] saveBrick;
    RootPane rootPane;
    BallController[] ballControllers = new BallController[5];

    public GamePane(RootPane rootPane)
    {
        //尺寸设置
        setMinSize(600, 800);
        setMaxSize(600, 800);

        //背景设置
        Image image = new Image(getClass().getResourceAsStream("../img/background.png"));
        setBackground(new Background(new BackgroundImage(image, null, null, null, null)));

        this.rootPane = rootPane;
        Brick brick2 = Brick.getConBrick();
        conBrickController = new ConBrickController(brick2, this);
        saveBrick = CreateBrick.createBrick(this);
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                getChildren().add(saveBrick[i][j].getShape());
            }
        }
        getChildren().add(conBrickController.getShape());

        //鼠标操作方式
        setOnMouseMoved(e -> conBrickController.MouseMove(e));

        //键盘操作方式
        setOnKeyPressed(e -> conBrickController.KeyMove(e));

        addBall();
        //Thread t1 = new Thread(ballController2);
//        Thread t = new Thread(conBrickController);

        //t1.start();
//        conBrickController.start();

    }


    //图形添加
    public void addShape()
    {
        CreateBrick.addBrick(saveBrick, this);
        int i = 0;
        for (int j = 0; j < 6; j++)
        {
            getChildren().add(saveBrick[i][j].getShape());
        }
    }

    /**
     * 删除球 num代表数组编号
     * @param ballController
     */
    public void deleteBall(BallController ballController)
    {
        try
        {
            int num = ballController.getNum();
            ballNum[num] = false;
            ballController.ballFade();
            getChildren().remove(ballControllers[num]);
            if(calculatinBall() <= 0){
                addBall();
                addShape();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int calculatinBall()
    {
        int num = 0;
        for (int i = 0; i < 3; i++)
        {
            if (ballNum[i])
            {
                num++;
            }
        }
        return num;
    }

    public boolean addBall()
    {

        boolean flag = false;
        Brick conBrick = this.conBrickController.getShape();

        for (int i = 0; i < 3; i++)
        {
            if (!ballNum[i])
            {
                int num = i;
                Ball ball = new Ball((conBrick.getX() + Math.random() * conBrick.getWidth()),
                        conBrick.getY(), 20, Color.CORNFLOWERBLUE);

                ballNum[num] = true;
                ballControllers[num] = new BallController(ball, this, num);

                double dx, dy;
                dx = Math.random() * 0.5;
                dy = 0.6 - dx + 0.1;
                ballControllers[num].setDx(dx);
                ballControllers[num].setDy(dy);

                getChildren().add(ballControllers[num].getShape());
//                ballThread[num] = new Thread(ballControllers[num]);
                ballControllers[num].start();

                flag = true;

                break;
            }
        }

        return flag;
    }

    /**
     * 调用砖块碰撞判断
     *
     * @param ball 球
     * @return
     */
    public int brickCatch(Ball ball)
    {
        int flag = CreateBrick.isBrick(ball, saveBrick, this);
        return flag;
    }

    /**
     * 界面删除砖块
     *
     * @param flag i = flag / 10 j = flag % 10
     */
    public void deleteBrickShape(int flag)
    {
        getChildren().remove(saveBrick[flag / 10][flag % 10]);
        //测试积分器
        rootPane.pointController.addPoint(10);
        /*=-=====*/
    }

    public void ballDelete()
    {
        for (int i = 0; i < 3; i++)
        {
            if (ballNum[i])
            {
                if (!ballControllers[i].isBallLive())
                {
                    deleteBall(ballControllers[i]);
                }
            }
        }
    }

    /**
     * 增加道具球
     */
    public  void propsAddBall(){
        for(int i = 0; i <  2; i++)
           addBall();
    }

    /**
     * delete道具球动画
     * @param propsController
     */
    public void  deletePropsBall(PropsController propsController){
        propsController.ballFade();
        getChildren().remove(propsController);
    }

    public ConBrickController getConBrickController()
    {
        return conBrickController;
    }

}
