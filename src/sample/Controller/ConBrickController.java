package sample.Controller;

import sample.GameObject.Brick;
import sample.GamePane;

public class ConBrickController extends BrickController
{

    public ConBrickController(Brick brick, GamePane pane,int hp)
    {
        super(brick, pane, hp);
//        super.setAnimation(new Timeline(new KeyFrame(Duration.millis(10), e -> con())));
//        this.brick = brick;
    }

    public Brick getShape()
    {
        return super.getShape();
    }

    public void moveLeft()
    {
        System.out.println("A");
        Brick brick = super.getShape();
        brick.setX(brick.getX() - 10);
    }

    public void moveRight()
    {
        System.out.println("D");
        Brick brick = super.getShape();
        brick.setX(brick.getX() + 10);
    }
    @Override
    public void run()
    {

    }
}
