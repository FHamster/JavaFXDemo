package sample.myUtil;

import sample.gameObjectView.Ball;
import sample.gameObjectView.Brick;

import java.awt.*;

public class Discalculation {


    public static double disVC(Ball ball, Brick brick){

        double v = 0;

        Point c = new Point();
        Point p = new Point();
        Point h = new Point();
        Point dis = new Point();

        c.setLocation((int) brick.getX() + (int)brick.getWidth() / 2, (int)brick.getY() + (int)brick.getHeight() / 2);
        p.setLocation((int)Math.abs(ball.getCenterX() - (int)c.getX()), (int)Math.abs(ball.getCenterY() - c.getY()));
        h.setLocation((int)brick.getWidth()  / 2, (int)brick.getHeight() / 2);
        dis.setLocation((int)p.x - h.x, (int)p.y - h.y);
        dis.setLocation((int)Math.max(dis.x,0),(int)Math.max(dis.y,0));
        if(ball.getRadius()==10)
            System.out.println( brick);
        return  Math.sqrt(dis.x * dis.x + dis.y * dis.y);
    }
}
