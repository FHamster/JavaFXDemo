package sample.Controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.GameObject.Brick;

public class BrickController extends AbstractController
{
    private int HP;
    private Brick brick;


    public BrickController(Brick brick, Pane pane, int HP)
    {
        super(pane, null);
        FadeTransition FT = new FadeTransition(new Duration(1000), brick);
        FT.setFromValue(1.0);
        FT.setToValue(0.0);
        FT.setCycleCount(Animation.INDEFINITE);
        this.setAnimation(FT);

//        super.setAnimation();
        ;

        this.HP = HP;
        this.brick = brick;
    }

    @Override
    public void run()
    {
        this.getAnimation().play();
    }

    @Override
    public Brick getShape()
    {
        return brick;
    }
}
