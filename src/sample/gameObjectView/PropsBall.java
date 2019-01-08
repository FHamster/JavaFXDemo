package sample.gameObjectView;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import sample.gameObjectView.Ball;

import javax.swing.*;

public class PropsBall extends Ball {

    private int propsNum;

    public PropsBall(double centerX, double centerY, double radius, Paint fill, int propsNum){
        super(centerX, centerY, radius, fill);

        this.propsNum = propsNum;
    }

    public int getPropsNum() {
        return propsNum;
    }
}
