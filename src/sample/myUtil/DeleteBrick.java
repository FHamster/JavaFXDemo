package sample.myUtil;


import sample.GameOverView;
import sample.GamePane;
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
    public static boolean myDelete(BrickController[][] save, int flag, GamePane gamePane){
        if(flag == -1 || save[flag / 10][flag % 10] == null)
            return false;
        else {
            //消失动画
            save[flag / 10][flag % 10].BrickFade();
            gamePane.getChildren().remove(save[flag / 10][flag % 10]);
            save[flag / 10][flag % 10] = null;
            return  true;
        }
    }

    /**
     * 重置游戏删除全部砖块
     * @param save
     */
    public static void myAllDelete(BrickController[][] save, GamePane gamePane){

        for(int i = 0; i <= 18; i++){
            for(int j = 0; j < 6; j ++){
                if(save[i][j] == null)
                    continue;
                else{
                    save[i][j].BrickFade();
                    gamePane.getChildren().remove(save[i][j]);
                    save[i][j] = null;
                }
            }
        }
    }

}
