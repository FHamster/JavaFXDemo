package sample.infoPane.bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TimerBean
{
    private IntegerProperty min = new SimpleIntegerProperty(0);
    private IntegerProperty sec = new SimpleIntegerProperty(0);

    public TimerBean()
    {
        this(0, 0);
    }

    public TimerBean(int min, int sec)
    {
        setMin(min);
        setSec(sec);
    }



    //getter and setter
    /*=================================================================================*/

    public void setMin(int min)
    {
        this.min.setValue(min);
    }

    public void setSec(int sec)
    {
        this.sec.setValue(sec);
    }

    public int getMin()
    {
        return min.getValue();
    }

    public int getSec()
    {
        return sec.getValue();
    }

    public IntegerProperty minProperty()
    {
        return min;
    }

    public IntegerProperty secProperty()
    {
        return sec;
    }
    /*=================================================================================*/

    @Override
    public String toString()
    {
        return String.format("%02d:%02d", min, sec);
    }
}
