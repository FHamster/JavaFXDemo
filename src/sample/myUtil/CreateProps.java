package sample.myUtil;

import com.sun.deploy.util.BlackList;
import javafx.scene.paint.Color;
import sample.GamePane;
import sample.controller.PropsController;
import sample.propsView.PropsBall;


public class CreateProps {

    /**
     * 创建道具球， 最后一位参数为道具种类
     * @return
     */

    public static PropsBall createPropsBall(GamePane pane){
        double weight = pane.getWidth() - 60;
        int num = (int)(Math.random() * 4) + 1;
        return new PropsBall(Math.random() * weight, 0, 15,  Color.rgb(255, 0, 0), num );
    }

    public static PropsController cratePropController(GamePane pane){
        return new PropsController(createPropsBall(pane), pane);
    }
}
