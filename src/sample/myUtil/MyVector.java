package sample.myUtil;


import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

/**
 * 向量类，你在这里把向量的计算写成方法
 * 包括内外积，加减
 * 会有用
 * 最好养成写文档注释的习惯
 * 你也可以适当改改
 */
public class MyVector
{
    //向量的序偶对
    private double x;
    private double y;

    public MyVector(Line line)
    {
        this(line.getEndX() - line.getStartX(), line.getEndY() - line.getStartY());
    }
    public MyVector(Point2D start, Point2D end)
    {
        this(end.getX() - start.getX(), end.getY() - start.getY());
    }

    public MyVector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
}
