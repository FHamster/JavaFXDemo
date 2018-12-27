package sample.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import sample.GameObjectView.Ball;
import sample.GameObjectView.Brick;
import sample.GamePane;
import sample.myUtil.Discalculation;

/**
 * ball的控制类
 * 操作ball
 * @author gaoxin
 */
public class BallController extends AbstractController
{
    //目标控制对象
    private  Ball ball;
    private  int num;
    //
    private double dx;
    private double dy;
    private boolean alive;

    public BallController(Ball ball, GamePane pane ,int num)
    {
        super(pane, null);
        super.setAnimation(new Timeline(new KeyFrame(Duration.millis(1), e -> move())));
        this.ball = ball;
        this.num = num;
        getAnimation().setCycleCount(Animation.INDEFINITE);

        dx = 0.3;
        dy = 0.3;
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
        Brick conBrick = getPane().getConBrickController().getShape();

        boolean flag = false;
        // Check boundaries
        if (x < radius || x > pane.getWidth() - radius)
        {
            dx *= -1; // Change ball move direction
            flag = true;
        } else if (y < radius || y > pane.getHeight() - radius)
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
    boolean isconBrick (){
        Brick conBrick = getPane().getConBrickController().getShape();

        boolean flag = false;
        // Check boundaries
        if (Discalculation.disVC(ball, getPane().getConBrickController().getShape()) <= ball.getRadius())
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
    public void ballFade()
    {
        getAnimation().play();
    }

    public void move()
    {
        isBound();
        isconBrick();
        int flag = getPane().brickCatch(ball);
        disChange(flag);
        getPane().de();
        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);
    }


    @Override
    public void run()
    {
        getAnimation().play();

    }
}
