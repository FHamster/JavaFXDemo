package sample.Controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.GameObjectView.Brick;
import sample.GamePane;

import javax.swing.*;

/**
 * 砖块的控制类
 * 操作砖块
 * @author gaoxin
 */
public class  BrickController extends AbstractController
{
    //目标控制对象
    private Brick brick;

    //砖块生命值
    //hp为0时砖块死亡
    private int HP;

    public BrickController(Brick brick,GamePane pane, int HP)
    {
        super(pane, null);
        FadeTransition FT = new FadeTransition(Duration.millis(1000), brick);
        FT.setFromValue(1.0);
        FT.setToValue(0.0);
//        FT.setCycleCount(Animation.INDEFINITE);
        setAnimation(FT);
//        super.setAnimation();
        ;

        this.HP = HP;
        this.brick = brick;


//        BrickFade();
    }

    public void BrickFade()
    {
        getAnimation().play();
    }

    @Override
    public void run()
    {
//        getAnimation().play();
    }

    @Override
    public Brick getShape()
    {
        return brick;
    }

}
