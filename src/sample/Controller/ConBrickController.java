package sample.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.GameObjectView.Brick;

public class ConBrickController extends AbstractController
{
    Brick brick;

    public ConBrickController(Brick brick, Pane pane)
    {
        super(pane, null);
        super.setAnimation(new Timeline(new KeyFrame(Duration.millis(10))));

        this.brick = brick;
    }

    public Brick getShape()
    {
        return brick;
    }

    public void moveLeft()
    {
        brick.setX(brick.getX() - 5);
    }

    public void moveRight()
    {
        brick.setX(brick.getX() + 5);
    }

    @Override
    public void run()
    {
//        getAnimation().play();
    }
}
