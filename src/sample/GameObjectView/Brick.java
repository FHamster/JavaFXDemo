package sample.GameObjectView;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


/**
 *砖块/挡板视图类，用于制造砖块和挡板的view
 */
public class Brick extends Rectangle
{
    /**
     * 制造可控制挡板view的工厂方法
     *
     * @return
     */
    public static Brick getConBrick()
    {
        return new Brick(0, 0, 250, 20, Color.LAVENDER, 10, 10);
    }

    /**
     * 制造砖块view的工厂方法
     *
     * @return
     */
    public static Brick getRandStdBrick()
    {
        return new Brick(0, 0, 100, 30, Color.CYAN, 15, 15);
    }

    /**
     * 制造砖块/挡板view的构造方法
     *
     * @param x         初始位置x
     * @param y         初始位置y
     * @param width     宽度
     * @param height    高度
     * @param paint     填充颜色
     * @param arcHeight 边角弧度
     * @param arcWidth  边角弧度
     */
    public Brick(double x, double y, double width, double height, Paint paint, double arcHeight, double arcWidth)
    {
        super(x, y, width, height);
        super.setFill(paint);
        super.setArcHeight(arcHeight);
        super.setArcWidth(arcWidth);
    }
}
