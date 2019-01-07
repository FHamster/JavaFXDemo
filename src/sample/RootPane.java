package sample;

import javafx.animation.Animation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.infoPane.controller.PointController;
import sample.infoPane.controller.TimerController;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.peer.LabelPeer;

public class RootPane extends BorderPane
{
    GamePane gamePane = new GamePane(this);
    InfoPane infoPane = new InfoPane();
    ButtonPane buttonPane = new ButtonPane();

    //控制器
    TimerController timerController = new TimerController(gamePane);
    PointController pointController = new PointController(gamePane);

    //按键
    Button startButton = new Button("开始");
    Button pauseButton = new Button("暂停");
    Button restarButton = new Button("重玩");

    Label label = new Label();

    public RootPane()
    {
        //pane初始化
        IniGamePane();
        IniInfoPane();
        IniButtonPane();
      //  labelPane();

        //设置布局
        setCenter(gamePane);
        setRight(infoPane);
        setLeft(buttonPane);

    }

    public void IniGamePane()
    {
    }

    public void IniButtonPane()
    {
        //TODO 按键事件指定
        startButton.setOnAction(e ->
        {
            timerController.startTimer();
            gamePane.conBrickController.setMove(true);
            for(int i = 0 ; i < 3; i++){
                if(gamePane.ballNum[i])
                    gamePane.ballControllers[i].start();
            }
        });

        pauseButton.setOnAction(e ->
        {
            timerController.getAnimation().stop();
            gamePane.conBrickController.setMove(false);
            for(int i = 0 ; i < 3; i++){
                if(gamePane.ballNum[i])
                    gamePane.ballControllers[i].getAnimation().stop();
            }
        });


        restarButton.setOnAction(e ->
        {
            gamePane.getChildren().clear();
            gamePane = null;

            gamePane = new GamePane(this);
            setCenter(gamePane);
            gamePane.requestFocus();

            gamePane.setReopen(true);
            pointController.setPoint(0);

            gamePane.reopenGame();
        });
        buttonPane.getChildren().add(startButton);
        buttonPane.getChildren().add(pauseButton);
        buttonPane.getChildren().add(restarButton);

    }

    public void IniInfoPane()
    {
        infoPane.getChildren().add(timerController.getView());
        infoPane.getChildren().add(pointController.getView());
        timerController.startTimer();
    }

    public  void labelPane(){
        label.setLayoutX(200);
        label.setLayoutY(250);
        label.setText("GAME OVER  \n SCORE : " + pointController.getPoint());
        label.setFont(Font.font ("Verdana", FontWeight.BOLD,50));
        label.setTextFill(Color.DARKSEAGREEN);
        gamePane.getChildren().add(label);
    }

    public void setGamePane(GamePane gamePane) {
        this.gamePane = gamePane;
    }
}
