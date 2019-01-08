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
 * 指定结束语句
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

    /**
     * 分数增加
     * @param add
     */
    public void addPoint(int add)
    {
        bean.setPoint(bean.getPoint() + add);
        view.repaint();
        //为结束页面添加分数
        gameOverView.setPoint(bean.getPoint());
    }

    public PointView getView()
    {
        return view;
    }

    /**
     * 用于重置分数以及修改
     * @param point
     */
    public void setPoint(int point) {
        bean.setPoint(point);
        view.repaint();
    }

    public  int getPoint(){
        return (int)bean.getPoint();
    }
}
