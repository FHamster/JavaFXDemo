package sample;


import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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
import sample.myUtil.DeleteBrick;

import javax.lang.model.element.NestingKind;

/**
 * 主要游戏视图控制
 * 包含 挡板，方块，球， 道具
 * 提供函数调用中介
 */
public class GamePane extends Pane
{
    ConBrickController conBrickController;

    boolean[] ballNum = new boolean[5];
    BrickController[][] saveBrick;
    RootPane rootPane;
    BallController[] ballControllers = new BallController[5];

    private GameOverView gameOverView;

    private boolean reopen;
    private boolean firstBall = true;

    public GamePane(RootPane rootPane)
    {
        this.reopen = false;

        //尺寸设置
        setMinSize(600, 800);
        setMaxSize(600, 800);

        //背景设置
        Image image = new Image(getClass().getResourceAsStream("../img/background.png"));
        setBackground(new Background(new BackgroundImage(image, null, null, null, null)));

        this.rootPane = rootPane;


        //鼠标操作方式
        setOnMouseMoved(e -> conBrickController.MouseMove(e));

        //键盘操作方式
        setOnKeyPressed(e -> conBrickController.KeyMove(e));

        addBrick();

        addConrBrick();

        addBall();



    }
    //初始化砖块
    public void addBrick(){
        saveBrick = CreateBrick.createBrick(this);
        for (int i = 0; i < 18; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                if(saveBrick[i][j] != null)
                getChildren().add(saveBrick[i][j].getShape());
            }
        }
    }

    //添加挡板
    public void addConrBrick(){

        Brick brick = Brick.getConBrick();
        conBrickController = new ConBrickController(brick, this);
        getChildren().add(conBrickController.getShape());

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

        if(CreateBrick.boundBrick(saveBrick)){
            rootPane.stopAll();
            gameOverView.labelPane();
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
     * 添加球以及firstBall 判定
     * @return
     */
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
                ballControllers[num] = new BallController(ball, this, num,firstBall);

                double dx, dy;
                dx = Math.random() * 0.5;
                dy = 0.6 - dx + 0.1;

                if(firstBall){
                    dx = 0;
                    dy = 0;
                    firstBall = false;
                }

                ballControllers[num].setDx(dx);
                ballControllers[num].setDy(dy);

                getChildren().add(ballControllers[num].getShape());
                ballControllers[num].start();

                this.setOnKeyPressed(e -> ballControllers[num].KeyCilck(e));

                flag = true;

                break;
            }
        }

        return flag;
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
            ballController.setDx(0);
            ballController.setDy(10);
//            ballController.stopAnimation();
            getChildren().remove(ballControllers[num]);
            if(calculatinBall() <= 0){
                rootPane.stopAll();
                gameOverView.labelPane();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 删除挡板
     */
    public void deleteConBrick(){
        conBrickController.conBrickFade();
        getChildren().remove(conBrickController);
        conBrickController = null;
    }

    /**
     * 剩余球数量计算
     * @return
     */
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

    /**
     * 删除数组内的全部球
     */
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
     * delete道具球动画
     * @param propsController
     */
    public void  deletePropsBall(PropsController propsController){

        propsController.ballFade();
        getChildren().remove(propsController);
    }

    //重置标记
    public void reopenGame(){
        if(this.reopen){
            DeleteBrick.myAllDelete(saveBrick);
            this.getChildren().removeAll(this);
            addBrick();

            deleteConBrick();
            addConrBrick();

            for(int i = 0; i < 3; i++)
                ballDelete();
            this.firstBall = true;
           // addBall();

            this.reopen = false;
        }
    }

    public void setGameOverView(GameOverView gameOverView) {
        this.gameOverView = gameOverView;
    }

    public ConBrickController getConBrickController()
    {
        return conBrickController;
    }

    public void setReopen(boolean reopen) {
        this.reopen = reopen;
    }

    public RootPane getRootPane()
    {
        return rootPane;
    }
}
