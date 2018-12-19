package sample.myUtil;


import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public interface MyUtil
{
    /**
     * 计算点到线段的距离
     * 这个比较容易出bug多调试吧
     * @param p    需要判断的点
     * @param line 需要判断的线段
     * @return 点到线段的距离, 不存在返回-1
     */
    double LineToPointLength(Point2D p, Line line);
}
