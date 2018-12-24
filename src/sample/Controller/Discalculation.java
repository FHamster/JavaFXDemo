package sample.Controller;

import sample.GameObjectView.Ball;
import sample.GameObjectView.Brick;

import java.awt.*;

public class Discalculation {


    public static double disccl(Ball ball, Rectangle rec){

        double dis = 0;

        Point p = new Point((int)rec.getCenterX(), (int)rec.getCenterY());
        Point c = new Point((int)Math.abs(ball.getCenterX() - p.x), (int)Math.abs(ball.getCenterY() - p.y));

        dis = Math.sqrt((rec.getWidth() / 2 - c.x) * (rec.getWidth() / 2 - c.x) +
                (rec.getHeight() / 2 - c.y) * (rec.getHeight() / 2 - c.y));

        double disco = Math.sqrt(c.x * c.x + c.y * c.y);


        return  Math.min(dis, disco) ;
    }

    public static double disVC(Ball ball, Brick brick){

        double v = 0;

        Point c = new Point();
        Point p = new Point();
        Point h = new Point();
        Point dis = new Point();

        c.setLocation((int) brick.getX() + (int)brick.getWidth() / 2, (int)brick.getY() + (int)brick.getHeight() / 2);
        p.setLocation((int)Math.abs(ball.getCenterX() - (int)c.getX()), (int)Math.abs(ball.getCenterY() - c.getY()));
        h.setLocation((int)brick.getWidth()  / 2, (int)brick.getY() /2);
        dis.setLocation((int)p.x - h.x, (int)p.y - h.y);
        dis.setLocation((int)Math.max(dis.x,0),(int)Math.max(dis.y,0));
        if(ball.getRadius()==10)
            System.out.println( brick);
        return  Math.sqrt(dis.x * dis.x + dis.y * dis.y);
    }
}
