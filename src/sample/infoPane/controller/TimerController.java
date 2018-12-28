package sample.infoPane.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import jdk.nashorn.internal.runtime.Timing;
import sample.infoPane.bean.TimerBean;
import sample.infoPane.view.TimerView;

import javax.lang.model.element.AnnotationMirror;

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
}
