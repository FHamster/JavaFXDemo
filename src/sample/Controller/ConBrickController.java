package sample.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sample.GameObjectView.Brick;
import sample.GamePane;

/**
 * 挡板的控制类
 * 操作挡板
 * @author gaoxin
 */
public class ConBrickController extends AbstractController
{
    private Brick brick;

    public ConBrickController(Brick brick, GamePane pane)
    {
        super(pane, null);
        super.setAnimation(new Timeline(new KeyFrame(Duration.millis(1))));

        this.brick = brick;
    }

    public Brick getShape()
    {
        return brick;
    }

    public void moveLeft()
    {
        brick.setX(brick.getX() - 10);
    }

    public void moveRight()
    {
        brick.setX(brick.getX() + 10);
    }

    @Override
    public void run()
    {
//        getAnimation().play();
    }
}
