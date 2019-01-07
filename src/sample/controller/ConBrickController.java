package sample.controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.ContinueNode;
import sample.gameObjectView.Brick;
import sample.GamePane;

/**
 * 挡板的控制类
 * 操作挡板
 */
public class ConBrickController extends AbstractController {
    //目标控制对象
    private Brick brick;
    private MouseEvent last;
    private boolean move;

    public ConBrickController(Brick brick, GamePane pane) {
        super(pane, null);
        this.brick = brick;
        this.move = true;
    }

    public Brick getShape() {
        return brick;
    }

    @Override
    public void start() {
//        getAnimation().play();
    }
    /*==================================================================================*/

    /**
     * 基于鼠标的砖块移动方法
     * 触碰边界必须反向移动
     */
    public void MouseMove(MouseEvent e) {
        if (move) {
            if (last == null)
                last = e;
            if ((brick.getX() > 30 || e.getX() - last.getX() > 0) && (brick.getX() + brick.getWidth() < getPane().getWidth() - 30 || e.getX() - last.getX() < 0)) {
                brick.setX(e.getX() - brick.getWidth() / 2);
                last = e;
            }
        }

    }

    /**
     * 基于键盘的砖块移动方法
     */

    public void KeyMove(KeyEvent e) {
        switch (e.getCode()) {
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

    public void conBrickFade()
    {
        FadeTransition fade = new FadeTransition(Duration.millis(1000), brick);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setCycleCount(2);
        fade.play();
    }

    public void setMove(boolean move) {
        this.move = move;
    }
    /*==================================================================================*/
}
