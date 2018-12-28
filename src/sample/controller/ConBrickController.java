package sample.controller;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.gameObjectView.Brick;
import sample.GamePane;

/**
 * 挡板的控制类
 * 操作挡板
 */
public class ConBrickController extends AbstractController
{
    //目标控制对象
    private Brick brick;

    public ConBrickController(Brick brick, GamePane pane)
    {
        super(pane, null);
        this.brick = brick;
    }

    public Brick getShape()
    {
        return brick;
    }

    @Override
    public void start()
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
