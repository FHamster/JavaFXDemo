package sample.Controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import sample.GameObjectView.Brick;
import sample.GamePane;

public class  BrickController extends AbstractController
{
    private int HP;
    private Brick brick;

    public BrickController(Brick brick, GamePane pane, int HP)
    {
        super(pane, null);
        FadeTransition FT = new FadeTransition(Duration.millis(1000), brick);
        FT.setFromValue(1.0);
        FT.setToValue(0.0);
        FT.setCycleCount(Animation.INDEFINITE);
        setAnimation(FT);

//        super.setAnimation();
        ;

        this.HP = HP;
        this.brick = brick;
    }

    @Override
    public void run()
    {
        getAnimation().play();
    }

    @Override
    public Brick getShape()
    {
        return brick;
    }
}
