package sample.controller;

import javafx.animation.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import sample.gameObjectView.Ball;
import sample.gameObjectView.Brick;
import sample.GamePane;
import sample.myUtil.Discalculation;

import java.security.Key;

/**
 * ball的控制类
 * 操作ball
 */
public class BallController extends AbstractController
{
    //目标控制对象
    private  Ball ball;
    private  int num;
    //小球速度 存活
    private double dx;
    private double dy;
    private boolean alive;

    private boolean firstBall = false;

    public BallController(Ball ball, GamePane pane ,int num)
    {
        super(pane, null);
        super.setAnimation(new Timeline(new KeyFrame(Duration.millis(1), e -> move())));
        this.ball = ball;
        this.num = num;
        getAnimation().setCycleCount(Animation.INDEFINITE);
    }

    public BallController(Ball ball, GamePane pane ,int num, boolean firstBall)
    {
        super(pane, null);
        super.setAnimation(new Timeline(new KeyFrame(Duration.millis(1), e -> move())));
        this.ball = ball;
        this.num = num;
        this.firstBall = firstBall;
        getAnimation().setCycleCount(Animation.INDEFINITE);
    }

    public BallController(GamePane pane) {
        super(pane,null);
    }

    public int getNum() {
        return num;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public Ball getShape()
    {
        return ball;
    }

    /**
    判断边界
     */
    private boolean isBound()
    {
        double x = ball.getCenterX();
        double y = ball.getCenterY();
        double radius = ball.getRadius();
        Pane pane = this.getPane();

        boolean flag = false;
        // Check boundaries
        if (x < radius|| x > pane.getWidth() - radius)
        {
            dx *= -1; // Change ball move direction
            flag = true;
        } else if (y < -radius )
        {
            dy *= -1; // Change ball move direction
            flag = true;
        }

        return flag;
    }

    /**
     * 挡板碰撞判定
     * @return
     */
    private boolean isconBrick (){
        Brick conBrick = getPane().getConBrickController().getShape();

        boolean flag = false;
        // Check boundaries
        if (Discalculation.disVC(ball, getPane().getConBrickController().getShape()) <= ball.getRadius() + 1)
        {
            if((ball.getCenterY() < conBrick.getY() || ball.getCenterY() >conBrick.getY() + conBrick.getHeight())){
                dy *= -1;
                flag = true;
            }else if((ball.getCenterX() < conBrick.getX() || ball.getCenterX() > conBrick.getX() + conBrick.getWidth())){
                dx *= -1;
                flag = true;
            }
        }

        if(ball.getCenterX() > conBrick.getX() && ball.getCenterX() < conBrick.getX() + conBrick.getWidth() &&
                (ball.getCenterY() > conBrick.getY() && ball.getCenterY() < conBrick.getY() + conBrick.getHeight())){
            ball.setCenterY(ball.getCenterY() - 2 * ball.getRadius());
            dx *= -1;
            dy *= -1;
            flag = true;
        }


        return flag;
    }

    /**
     * 碰撞方向改变
     * @param flag
     */
    public void disChange(int flag){
        switch (flag){
            case 1 : dy *= -1;
                    break;
            case 2 : dx *= -1;
                    break;
            case 3 : ball.setCenterY(ball.getCenterY() - 2 * ball.getRadius());
                     dx *= -1;
                     dy *= -1;
                     break;
            default: break;

        }
    }

    /**
     * 小球存活判定
     * @return
     */
    public boolean isBallLive(){
        Brick conBrick = getPane().getConBrickController().getShape();

        if(ball.getCenterY() > conBrick.getHeight() + conBrick.getY()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 小球因为出界消失的方法
     */
    public void ballFade()
    {
        getAnimation().stop();
        FadeTransition fade = new FadeTransition(Duration.millis(1000), ball);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setCycleCount(2);
        fade.play();
    }

    /**
     * 小球移动的方法
     */
    public void move()
    {
        Brick conBrick = getPane().getConBrickController().getShape();

        isBound();
        isconBrick();
        int flag = getPane().brickCatch(ball);
        disChange(flag);
        getPane().ballDelete();

        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);

        if(firstBall){
            ball.setCenterX(conBrick.getX() + conBrick.getWidth() / 2);
            ball.setCenterY(conBrick.getY() - ball.getRadius() - 1.75);
        }
    }


    public void KeyCilck(KeyEvent e) {
        if(e.getCode() == KeyCode.SPACE && firstBall){
            dx = Math.random() * 0.5;
            dy = 0.6 - dx + 0.1;
            firstBall = false;
            getPane().getRootPane().startTimer();
        }
    }
    /**
     * 小球移动开始
     */
    @Override
    public void start()
    {
        getAnimation().play();
    }
}
