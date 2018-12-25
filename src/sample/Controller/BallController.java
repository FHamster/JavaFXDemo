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

    //
    private double dx;
    private double dy;

    public BallController(Ball ball, GamePane pane)
    {
        super(pane, null);
        super.setAnimation(new Timeline(new KeyFrame(Duration.millis(1), e -> move())));
        this.ball = ball;

        getAnimation().setCycleCount(Animation.INDEFINITE);

        dx = 0.3;
        dy = 0.3;
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
                ((ball.getCenterY() > conBrick.getY() && ball.getCenterY() < conBrick.getY() + conBrick.getHeight())
                || conBrick.getY() + conBrick.getHeight() / 2 - ball.getCenterY() < conBrick.getHeight() / 2 + ball.getRadius()) ){
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

    public void move()
    {
        isBound();
        isconBrick();
        int flag = getPane().isBrick(ball);
        dischange(flag);
        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);
    }

    @Override
    public void run()
    {
        getAnimation().play();
    }
}
