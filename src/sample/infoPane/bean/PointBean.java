package sample.infoPane.bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PointBean
{
    private IntegerProperty point = new SimpleIntegerProperty(0);

    public PointBean()
    { point.set(0);}



    public int getPoint()
    {
        return point.get();
    }

    public IntegerProperty pointProperty()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point.set(point);
    }
}
