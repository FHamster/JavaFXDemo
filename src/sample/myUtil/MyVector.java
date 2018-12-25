package sample.myUtil;


import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import sample.Controller.BrickController;
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
    public static int myFlag(Ball ball ,List<BrickController> list){
        int flag = -1;
        for(int i = 0; i < list.size(); i ++){
            if(Discalculation.disVC(ball, list.get(i).getShape()) <= ball.getRadius()){
                flag = i;
                break;
            }
        }
        return flag;
    }

    public static int myFlag(Ball ball ,BrickController[][] save){
        int flag = -1;
        for(int i = 0; i < 6; i ++){
            for(int j = 0; j < 6; j ++){
                if(save[i][j]  == null)
                    continue;
                if(Discalculation.disVC(ball, save[i][j].getShape()) <= ball.getRadius()){
                    flag = i * 10 + j;
                    return flag;
                }
            }
        }
        return flag;
    }

    public static boolean myDelete(List<BrickController> list, int flag){
        if(flag == -1 || list.size() <= 0)
            return false;
        else {
            //消失动画
            list.get(flag).BrickFade();
            list.remove(flag);
            return  true;
        }
    }

    public static boolean myDelete(BrickController[][] save, int flag){
        if(flag == -1 || save[flag / 10][flag % 10] == null)
            return false;
        else {
            //消失动画
            save[flag / 10][flag % 10].BrickFade();
            save[flag / 10][flag % 10] = null;
            return  true;
        }
    }
}
