package sample.myUtil;


import sample.gameObjectView.Ball;
import sample.gameObjectView.Brick;

import java.util.Random;

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

    /**
     * @param min
     * @param max
     * @return 返回分布在[min, max]的随机整数
     */
     static  int getRandomNumber(int min, int max)
    {
        if (min > max)
        {
            throw new IllegalArgumentException("min =" + min + ">" + "max=" + max);
        }
        return min + new Random().nextInt(max + 1 - min);
    }
}
