package sample.myUtil;

import sample.Controller.BrickController;
import sample.GameObjectView.Brick;
import sample.GamePane;

public class CreateBrick {
    //创建二维数组
    public  static BrickController[][] createBrick(GamePane pane){
        BrickController[][]  save = new BrickController[19][6];

        //给二维数组加入控制器
        //按照二维数组ij编号创造单个砖块视图对象
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j ++){
                save[i][j] = new BrickController(setBrick(i, j), pane, 1);
            }
        }
        return save;
    }

    //根据i，j定点砖块位置
    public static Brick setBrick(int i, int j){
        int dx,dy;
        dx = (int) Brick.getRandStdBrick().getX();
        dy = (int) Brick.getRandStdBrick().getY();
        int dwight = (int)Brick.getRandStdBrick().getWidth();
        int dhight = (int)Brick.getRandStdBrick().getHeight();

        dx += j * dwight;
        dy += i * dhight;

        Brick brick = Brick.getRandStdBrick();
        brick.setX(dx);
        brick.setY(dy);

        return  brick;
    }

    //砖块添加
    public static BrickController[][] addBrick (BrickController[][] save ,GamePane pane){

        int dhight = (int)Brick.getRandStdBrick().getHeight();

        for(int i = 18 - 1; i > 0; i --){
            for(int j = 6 - 1; j >= 0; j --){
                if(save[i-1][j] == null)
                    continue;
                else{
                    save[i][j] = save[i - 1][j];
                    double y = save[i][j].getShape().getY();
                    save[i][j].getShape().setY(y + dhight);
                }
            }
        }

        int i = 0;
        for(int j = 0; j < 6; j ++){
            save[i][j] = new BrickController(setBrick(i, j), pane, 1);
        }

        return save;
    }
}
