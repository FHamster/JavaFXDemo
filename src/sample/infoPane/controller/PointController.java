package sample.infoPane.controller;

import javafx.animation.Animation;
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
//    private Animation animation;

    public PointController(GamePane pane)
    {
        this.pane = pane;
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
