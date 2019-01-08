package sample.infoPane.controller;

import javafx.animation.Animation;
import sample.GameOverView;
import sample.GamePane;
import sample.infoPane.bean.PointBean;
import sample.infoPane.view.PointView;

/**
 * 计分器的控制器
 * 可对积分进行加减
 * 使用的时候只需要制造控制器对象
 * 指定视图对象的位置
 */

public class PointController
{
    private GamePane pane;
    private PointBean bean;
    private PointView view;
    private GameOverView gameOverView;

    public PointController(GamePane pane,GameOverView gameOverView)
    {
        this.pane = pane;
        this.gameOverView = gameOverView;
        bean = new PointBean();
        view = new PointView(bean.pointProperty());
    }

    public void subtractPoint(int subtract)
    {
        bean.setPoint(bean.getPoint() - subtract);
        view.repaint();
    }

    public void addPoint(int add)
    {
        bean.setPoint(bean.getPoint() + add);
        view.repaint();
        gameOverView.setPoint(bean.getPoint());
    }

    public PointView getView()
    {
        return view;
    }

    public void setPoint(int point) {
        bean.setPoint(point);
        view.repaint();
    }

    public  int getPoint(){
        return (int)bean.getPoint();
    }
}
