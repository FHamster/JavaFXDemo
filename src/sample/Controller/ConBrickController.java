package sample.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.GameObjectView.Brick;
import sample.GamePane;
import sample.RootPane;

import java.beans.EventHandler;

/**
 * 挡板的控制类
 * 操作挡板
 *
 * @author gaoxin
 */
public class ConBrickController extends AbstractController
{
    //目标控制对象
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

    @Override
    public void run()
    {
//        getAnimation().play();
    }
    /*==================================================================================*/

    /**
     * 基于鼠标的砖块移动方法
     */
    public void MouseMove(MouseEvent e)
    {
        brick.setX(e.getX() - brick.getWidth() / 2);

    }

    /**
     * 基于键盘的砖块移动方法
     */

    public void KeyMove(KeyEvent e)
    {
        switch (e.getCode())
        {
            case W:
                brick.setY(brick.getY() - 10);
                break;
            case S:
                brick.setY(brick.getY() + 10);
                break;
            case A:
                brick.setX(brick.getX() - 10);
                break;
            case D:
                brick.setX(brick.getX() + 10);
                break;
        }
    }

    /*==================================================================================*/

}
