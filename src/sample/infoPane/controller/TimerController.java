package sample.infoPane.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sample.infoPane.bean.TimerBean;
import sample.infoPane.view.TimerView;

/**
 * 计时器的控制器
 * 已经开放了开始计时、暂停、重置接口
 * 使用的时候只需要制造控制器对象
 */
public class TimerController
{
    private TimerBean bean;
    private TimerView view;
    private Animation animation;

    public TimerController()
    {
        bean = new TimerBean();
        view = new TimerView(bean.minProperty(), bean.secProperty());
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> Timing()));
        animation.setCycleCount(Animation.INDEFINITE);
    }

    private void Timing()
    {
        bean.addSec(1);
        view.repaint();
    }


    public void resetTimer()
    {
        animation.stop();
        bean.setMin(0);
        bean.setSec(0);
    }
    public void pauseTimer()
    {
        animation.pause();
    }
    public void startTimer()
    {
        animation.play();
    }

    public TimerView getView()
    {
        return view;
    }

//    public static int currentSec() {
//        return bean.getMin() * 60 + bean.getSec();
//    }
}
