package sample.infoPane.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.text.Text;
import sample.RootPaneController;
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
    private RootPaneController pane;
    private PointBean bean;
    private Text view;
    private StringProperty PointStr = new SimpleStringProperty();
//    private Animation animation;

    public PointController(RootPaneController pane)
    {
        this.pane = pane;
        this.bean = new PointBean();
        this.view = new PointView(bean.pointProperty());
    }

    public void subtractPoint(int subtract)
    {
        bean.setPoint(bean.getPoint() - subtract);
        PointStr.set(String.format("Point:%d", bean.getPoint()));
    }

    public void addPoint(int add)
    {
        bean.setPoint(bean.getPoint() + add);
        PointStr.set(String.format("Point:%d", bean.getPoint()));
        System.out.println(String.format("Point:%d", bean.getPoint()));
    }

    public StringProperty pointStrProperty()
    {
        return PointStr;
    }

    /*    public PointView getView()
    {
        return view;
    }*/
}
