package sample.myUtil;


import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import sample.GameObjectView.Ball;
import sample.GameObjectView.Brick;

import java.util.List;

/**
 * 向量类，你在这里把向量的计算写成方法
 * 包括内外积，加减
 * 会有用
 * 最好养成写文档注释的习惯
 * 你也可以适当改改
 */
public class MyVector
{
    public static int myFlag(Ball ball ,List<Brick> list){
        int flag = -1;
        for(int i = 0; i < list.size(); i ++){
            if(Discalculation.disVC(ball, list.get(i)) <= ball.getRadius()){
                flag = i;
                break;
            }
        }
        return flag;
    }

    public static boolean myDelete(List<Brick> list, int flag){
        if(flag == -1)
            return false;
        else {
            //消失动画
            list.remove(flag);
            return  true;
        }
    }
}
