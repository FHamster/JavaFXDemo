package sample.GameObject;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle
{
    public static Brick getRandStdBrick()
    {
        return new Brick(0, 0, 90, 30, Color.CYAN);
    }

    public Brick(double x, double y, double width, double height, Paint paint)
    {
        super(x, y, width, height);
        super.setFill(paint);
    }


}
