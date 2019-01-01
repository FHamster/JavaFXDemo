package sample.controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import sample.GamePane;
import sample.gameObjectView.Brick;
import sample.myUtil.Discalculation;
import sample.myUtil.ImageLoader;
import sample.propsView.PropsBall;
import sample.propsView.PropsBall;

public class PropsController extends BallController
{

    //球的控制
    private PropsBall ball;
    private ImageView view;

    private int propsNum;

    private double dx;
    private double dy;

    public PropsController(PropsBall ball, GamePane pane)
    {
        super(pane);
        super.setAnimation(new Timeline(new KeyFrame(Duration.millis(1), e -> move())));

        this.ball = ball;

        this.propsNum = ball.getPropsNum();
        numChoose(this.propsNum);

        //这个控制器有两个视图，一个用来显示一个用来表示视图位置
        // 这样感觉不好，结构比较差
        //可是代码都打好了，还是想办法解救吧

        //绑定view和ball坐标
        //注意这里image的坐标由左上角表示，ball坐标由中心表示

//        DoubleBinding add =
        view.xProperty().bind(ball.centerXProperty().subtract(ball.radiusProperty()));
        view.yProperty().bind(ball.centerYProperty().subtract(ball.radiusProperty()));
        view.opacityProperty().bind(ball.opacityProperty());

        //view.prop;

        getAnimation().setCycleCount(Animation.INDEFINITE);

        this.dx = 0;
        this.dy = Math.random() * 0.9 + 0.1;
    }

    public void numChoose(int num){
        switch (num){
            case 1 : this.view = new ImageView(ImageLoader.shadow);
               break;
            case 2: this.view = new ImageView(ImageLoader.addCon);
                break;
            case 3: this.view = new ImageView(ImageLoader.delCon);
                break;
            case 4:
                this.view = new ImageView(ImageLoader.bomb);
                break;

        }
    }
    /**
     * 四种道具，1 三分支 2 板增长 3 板缩短 4 炸弹
     *
     * @param propsNum 道具代号
     */
    public void propsShow(int propsNum)
    {
        Brick conBrick = getPane().getConBrickController().getShape();
        switch (propsNum)
        {
            case 1:
                getPane().propsAddBall();
                break;
            case 2:
            {
                if (conBrick.getWidth() >= 175)
                {
                    break;
                } else
                {
                    conBrick.setWidth(conBrick.getWidth() + 25);
                    break;
                }
            }
            case 3:
            {
                if (conBrick.getWidth() <= 255)
                {
                    break;
                } else
                {
                    conBrick.setWidth(conBrick.getWidth() - 25);
                    break;
                }
            }
            case 4:
            {
                //propsBoom()
            }
            default:
                break;
        }
    }

    private boolean isconBrick()
    {
        Brick conBrick = getPane().getConBrickController().getShape();

        boolean flag = false;
        // Check boundaries
        if (Discalculation.disVC(ball, conBrick) <= ball.getRadius())
        {
            flag = true;
        }

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
     *
     * @return
     */
    private boolean isBound()
    {
        double y = ball.getCenterY();
        double radius = ball.getRadius();
        Pane pane = this.getPane();

        boolean flag = false;

        if (y > pane.getHeight() - radius)
        {
            flag = true;
        }

        return flag;
    }

    public void move()
    {
        boolean flag = this.isconBrick();
        if (flag)
        {
            propsShow(propsNum);
        }
        if (flag || isBound())
        {
            getPane().deletePropsBall(this);
        }

        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);
    }

    //原来获取球视图的方法
    @Override
    public PropsBall getShape()
    {
        return ball;
    }

    //获取img视图
    public ImageView getView()
    {
        return view;
    }

    @Override
    public void start()
    {
        getAnimation().play();
    }
}
