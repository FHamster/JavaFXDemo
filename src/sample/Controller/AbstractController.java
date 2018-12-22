package sample.Controller;

import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public abstract class AbstractController implements Runnable
{
    private Pane pane;
    private Animation animation;

    public AbstractController(Pane pane, Animation animation)
    {
        this.pane = pane;
        this.animation = animation;
    }

    public Pane getPane()
    {
        return pane;
    }

    public void setPane(Pane pane)
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
