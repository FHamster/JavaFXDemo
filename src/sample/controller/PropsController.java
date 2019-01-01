package sample.controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import sample.GamePane;
import sample.gameObjectView.Brick;
import sample.myUtil.Discalculation;
import sample.propsView.PropsBall;
import sample.propsView.PropsBall;

public class PropsController extends BallController {

    //球的控制
    private PropsBall ball;
    private int propsNum;

    private double dx;
    private double dy;

    public PropsController(PropsBall ball, GamePane pane) {
        super(pane);
        super.setAnimation(new Timeline(new KeyFrame(Duration.millis(1), e -> move())));

        this.ball = ball;
        this.propsNum = ball.getPropsNum();

        getAnimation().setCycleCount(Animation.INDEFINITE);

        this.dx = 0;
        this.dy = Math.random() * 0.9 + 0.1;
    }

    /**
     * 四种道具，1 三分支 2 板增长 3 板缩短 4 炸弹
     *
     * @param propsNum 道具代号
     */
    public void propsShow(int propsNum) {
        Brick conBrick = getPane().getConBrickController().getShape();
        switch (propsNum) {
            case 1:
                getPane().propsAddBall();
                break;
            case 2:{
                if(conBrick.getWidth() >= 200)
                    break;
                else{
                    conBrick.setWidth(conBrick.getWidth() + 25);
                    break;
                }
            }
            case 3:{
                if(conBrick.getWidth() <= 25)
                    break;
                else{
                    conBrick.setWidth(conBrick.getWidth() - 25);
                    break;
                }
            }
            case 4:{
                //propsBoom()
            }
            default:
                break;
        }
    }
    private boolean isconBrick (){
        Brick conBrick = getPane().getConBrickController().getShape();

        boolean flag = false;
        // Check boundaries
        if (Discalculation.disVC(ball, conBrick) <= ball.getRadius())
            flag = true;

        return flag;
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
     * 判断出界
     * @return
     */
    private boolean isBound(){
        double y = ball.getCenterY();
        double radius = ball.getRadius();
        Pane pane = this.getPane();

        boolean flag = false;

        if(y > pane.getHeight() - radius)
            flag = true;

        return flag;
    }
    public void move() {
        boolean flag = this.isconBrick();
        if (flag) {
            propsShow(propsNum);
        }
        if(flag || isBound())
            getPane().deletePropsBall(this);

        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);
    }

    @Override
    public PropsBall getShape() {
        return ball;
    }

    @Override
    public void start() {
        getAnimation().play();
    }
}
