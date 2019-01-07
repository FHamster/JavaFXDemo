package sample.myUtil;


import sample.controller.BrickController;
import sample.gameObjectView.Ball;

public class DeleteBrick
{

    //碰撞砖块坐标搜索
    public static int myFlag(Ball ball ,BrickController[][] save){
        int flag = -1;
        for(int i = 0; i < 18; i ++){
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

    //砖块删除
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

    public static void myAllDelete(BrickController[][] save){

        for(int i = 0; i < 18; i++){
            for(int j = 0; j < 6; j ++){
                if(save[i][j] == null)
                    continue;
                else{
                    save[i][j].BrickFade();
                    save[i][j] = null;
                }
            }
        }
    }

}
