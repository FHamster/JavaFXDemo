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
 * 砖块的控制类
 * 操作砖块
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
        } else if (y < radius)
        {
            dy *= -1; // Change ball move direction
            flag = true;
        }

        return flag;
    }

    boolean isconBrick (){
        double x = ball.getCenterX();
        double y = ball.getCenterY();
        double radius = ball.getRadius();
        Pane pane = this.getPane();
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

    public void dischange(int flag){
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

    public  boolean isBallLive(){
        Brick conBrick = getPane().getConBrickController().getShape();

        if(ball.getCenterY() > conBrick.getHeight() + conBrick.getY()){
            return false;
        }else{
            return true;
        }
    }

    public void move()
    {
        isBound();
        isconBrick();
        int flag = getPane().isBrick(ball);
        dischange(flag);
        this.alive = isBallLive();
        if(!alive){
            getPane().deleteBall(this);

        }
        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);
    }


    @Override
    public void run()
    {
        getAnimation().play();

    }
}
