package sample.GameObjectView;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.myUtil.MyUtil;


/**
 * 砖块/挡板视图类，用于制造砖块和挡板的view
 */
public class Brick extends Rectangle
{

    //配色表
    private static Color[] colorShell = {
            Color.rgb(95, 217, 205),
            Color.rgb(234, 247, 134),
            Color.rgb(255, 181, 161),
            Color.rgb(184, 255, 184),
            Color.rgb(184, 244, 255),
    };

    //标准砖块的长宽
    private static final int StdBrickWidth = 90;
    private static final int StdBrickHeight = 30;


    //控制挡板的长宽
    private static final int ConBrickWidth = 100;
    private static final int ConBrickHeight = 20;

    /**
     * 制造可控制挡板view的工厂方法
     *
     * @return
     */
    public static Brick getConBrick()
    {
        return new Brick(300, 700, ConBrickWidth, ConBrickHeight, colorShell[0], 10, 10);
    }

    /**
     * 制造砖块view的工厂方法
     *
     * @return
     */
    public static Brick getRandStdBrick()
    {
//        Color color = ;
        return new Brick(30, 0, StdBrickWidth, StdBrickHeight, colorShell[MyUtil.getRandomNumber(1,4)], 15, 15);
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
