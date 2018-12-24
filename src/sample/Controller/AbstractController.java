package sample.Controller;

import javafx.animation.Animation;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import sample.GamePane;

public abstract class AbstractController implements Runnable
{
    private GamePane pane;
    private Animation animation;

    public AbstractController(GamePane pane, Animation animation)
    {
        this.pane = pane;
        this.animation = animation;
    }

    public GamePane getPane()
    {
        return pane;
    }

    public void setPane(GamePane pane)
    {
        this.pane = pane;
    }

    public Animation getAnimation()
    {
        return animation;
    }

    public void setAnimation(Animation animation)
    {
        this.animation = animation;
    }

    public abstract Shape getShape();
}
