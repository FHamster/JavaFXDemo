package sample.myUtil;

import javafx.scene.paint.Color;
import sample.GameOverView;
import sample.GamePane;
import sample.controller.PropsController;
import sample.propsView.PropsBall;


public class CreateProps {

//    private static Image BoobImg
    /**
     * 创建道具球， 最后一位参数为道具种类
     * @return
     */

    public static PropsBall createPropsBall(GamePane pane){
        double weight = pane.getWidth() - 60;
        int num = (int)(Math.random() * 4) + 1;
        return new PropsBall(30 + Math.random() * 540, 0, 15, Color.RED, num);
    }

    /**
     * 添加道具控制类
     * @param pane 游戏总视图
     * @param gameOverView 💣调用视图
     * @return
     */
    public static PropsController cratePropController(GamePane pane , GameOverView gameOverView){
        return new PropsController(createPropsBall(pane), pane, gameOverView);
    }

}
