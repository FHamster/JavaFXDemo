package sample.infoPane.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sample.GameOverView;
import sample.GamePane;
import sample.controller.PropsController;
import sample.infoPane.bean.TimerBean;
import sample.infoPane.view.TimerView;
import sample.myUtil.CreateProps;

/**
 * 计时器的控制器
 * 已经开放了开始计时、暂停、重置接口
 * 使用的时候只需要制造控制器对象
 * 指定视图对象的位置
 */
public class TimerController
{
    private GamePane pane;
    private TimerBean bean;
    private TimerView view;
    private Animation animation;
    private GameOverView gameOverView;
    private PropsController test;

    public TimerController(GamePane pane, GameOverView gameOverView)
    {
        this.pane = pane;
        this.gameOverView = gameOverView;
        bean = new TimerBean();
        view = new TimerView(bean.minProperty(), bean.secProperty());
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> Timing()));
        animation.setCycleCount(Animation.INDEFINITE);
    }


    public void addSec(int addSec)
    {
//        System.out.println(1);
        bean.setSec(bean.getSec() + addSec);
        if (bean.getSec() >= 60)
        {
            bean.setSec(bean.getSec() - 60);
            bean.setMin(bean.getMin() + 1);
        }
    }

    private void Timing()
    {
        addSec(1);
        view.repaint();

        if (bean.getSec() % 10 == 0)
        {
            pane.addShape();

            test = CreateProps.cratePropController(pane, gameOverView);
            test.start();
            pane.getChildren().add(test.getView());
        }


    }


    public void resetTimer()
    {
        animation.stop();
        bean.setMin(0);
        bean.setSec(0);
        view.repaint();
    }

    public void pauseTimer()
    {
        animation.pause();
    }

    public void startTimer()
    {
        animation.play();
    }

    public int currentSec()
    {
        return bean.getMin() * 60 + bean.getSec();
    }

    public PropsController getTest() {
        return test;
    }

    public TimerView getView()
    {
        return view;
    }

    public Animation getAnimation() {
        return animation;
    }
}
