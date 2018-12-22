package sample.myUtil;


import sample.GameObject.ball.Ball;
import sample.GameObject.brick.Brick;

public interface MyUtil
{
    /**
     * 计算点到线段的距离
     * 这个比较容易出bug多调试吧
     * @param ball  目标ball
     * @param brick 需要判断的四边形
     * @return 点到线段的距离, 不存在返回-1
     */
    double LineToPointLength(Ball ball, Brick brick);
}
