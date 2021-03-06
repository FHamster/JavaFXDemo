package sample.infoPane.view;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 提供时间视图
 * 提供时间重绘
 */
public class TimerView extends Text
{
    private IntegerProperty min = new SimpleIntegerProperty(0);
    private IntegerProperty sec = new SimpleIntegerProperty(0);

    public TimerView(IntegerProperty BeanMin, IntegerProperty BeanSec)
    {
        min.bind(BeanMin);
        sec.bind(BeanSec);

        setFill(Color.FORESTGREEN);
        setFont(new Font(50));
        setStroke(Color.WHITE);

        repaint();
    }

    public void repaint()
    {
        setText(String.format("%02d:%02d", min.getValue(), sec.getValue()));
    }
}
