package sample.myUtil;

import sample.controller.BrickController;
import sample.gameObjectView.Ball;
import sample.gameObjectView.Brick;
import sample.GamePane;

public class CreateBrick {
    /**
     * 创建二维数组砖块
     */

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

    /**
     * 根据i，j定点砖块位置
     * @param i 横坐标位置
     * @param j 纵坐标位置
     * @return
     */
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

    /**
     * 砖块单行添加
     * @param save 存储砖块的二维数组
     * @param pane 添加面板
     * @return
     */
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

    /**
     *
     * @param ball 球
     * @param saveBrick 保存砖块的二维数组
     * @return
     */
    public static int isBrick(Ball ball , BrickController[][] saveBrick ,GamePane pane){

        int disController = -1;
        int flag = DeleteBrick.myFlag(ball,saveBrick);

        if(flag < 0)
            return  disController;

        Brick brick = saveBrick[flag / 10][flag % 10].getShape();

        if((ball.getCenterY() < brick.getX() || ball.getCenterY() > brick.getY() + brick.getHeight())){
            disController = 1;
        }else if((ball.getCenterX() < brick.getX() || ball.getCenterX() > brick.getX() + brick.getWidth())){
            disController = 2;
        }else if(ball.getCenterX() > brick.getX() && ball.getCenterX() < brick.getX() + brick.getWidth() &&
                ((ball.getCenterY() > brick.getY() && ball.getCenterY() < brick.getY() + brick.getHeight())
                        || brick.getY() + brick.getHeight() / 2 - ball.getCenterY() < brick.getHeight() / 2 + ball.getRadius()) ) {
            disController = 3;
        }

        DeleteBrick.myDelete(saveBrick,flag);
        if(flag >= 0)
            pane.deleteBrickShape(flag);
        return  disController;
    }
}
