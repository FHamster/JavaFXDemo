package sample.Controller;

import sample.GameObjectView.Ball;

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

    public static double disVC(Ball ball, Rectangle rec){

        double v = 0;

        Point p = new Point((int)rec.getCenterX(), (int)rec.getCenterY());
        Point c = new Point((int)Math.abs(ball.getCenterX() - p.x), (int)Math.abs(ball.getCenterY() - p.y));
        Point h = new Point((int)(rec.getWidth() / 2), (int)(rec.getHeight() / 2));
        Point dis = new Point((int)(c.x - h.x), (int)(c.y - h.y));

        dis.setLocation(Math.max(dis.x,0),Math.max(dis.y,0));
        if(ball.getRadius()==10)
        System.out.println( c);
        return  Math.sqrt(dis.x * dis.x + dis.y * dis.y);
    }
}
