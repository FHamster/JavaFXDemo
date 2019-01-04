package sample.myUtil;


import sample.gameObjectView.Ball;
import sample.gameObjectView.Brick;

import java.util.Random;

public class MyUtil
{
    /**
     * @param min
     * @param max
     * @return 返回分布在[min, max]的随机整数
     */
     public static int getRandomNumber(int min, int max)
    {
        if (min > max)
        {
            throw new IllegalArgumentException("min =" + min + ">" + "max=" + max);
        }
        return min + new Random().nextInt(max + 1 - min);
    }
}
