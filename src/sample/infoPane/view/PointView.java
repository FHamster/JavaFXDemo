package sample.infoPane.view;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 提供分数视图
 * 提供分数重绘
 */
public class PointView extends Text
{
    private IntegerProperty point = new SimpleIntegerProperty(0);

    public PointView(IntegerProperty point)
    {
        this.point.bind(point);

        setFill(Color.FORESTGREEN);
        setFont(new Font(50));
        setStroke(Color.WHITE);

        repaint();
    }

    public void repaint()
    {
        setText(String.format("Point:%d", point.getValue()));
    }
}
