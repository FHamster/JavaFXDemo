package sample.GameObject.ball;

import javafx.scene.paint.Color;

public class Ball
{
    private int ridus;
    private Color color;

    public Ball()
    {
        this(15, Color.GOLD);
    }

    public Ball(int ridus, Color color)
    {
        this.ridus = ridus;
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }

    public int getRidus()
    {
        return ridus;
    }
}
