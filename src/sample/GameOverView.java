package sample;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * 提供结束视图
 */
public class GameOverView {

    private int Point;
    private Label label = new Label();
    GamePane gamePane;


    public GameOverView(GamePane gamePane){
        this.Point = 0;
        this.gamePane = gamePane;
    }

    /**
     * 视图设置以及添加
     */
    public  void labelPane(){
        label.setLayoutX(200);
        label.setLayoutY(250);
        label.setText("GAME OVER  \n SCORE : " + getPoint());
        label.setFont(Font.font ("Verdana", FontWeight.BOLD,50));
        label.setTextFill(Color.DARKSEAGREEN);
        gamePane.getChildren().add(label);
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int point) {
        Point = point;
    }
}
