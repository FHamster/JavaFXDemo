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

/**
 * 所用视图控制
 */
public class RootPane extends BorderPane
{
    GamePane gamePane = new GamePane(this);
    InfoPane infoPane = new InfoPane();
    ButtonPane buttonPane = new ButtonPane();
    GameOverView gameOverView = new GameOverView(gamePane);

    //控制器
    TimerController timerController = new TimerController(gamePane,gameOverView);
    PointController pointController = new PointController(gamePane, gameOverView);

    //按键
    Button startButton = new Button("开始");
    Button pauseButton = new Button("暂停");
    Button restarButton = new Button("重玩");

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
        gamePane.setGameOverView(gameOverView);
    }

    public void IniButtonPane()
    {
        //TODO 按键事件指定
        //开始
        startButton.setOnAction(e ->
        {
            timerController.startTimer();
            gamePane.conBrickController.setMove(true);
            if(timerController.getTest() != null)
                timerController.getTest().start();
            for(int i = 0 ; i < 3; i++){
                if(gamePane.ballNum[i])
                    gamePane.ballControllers[i].start();
            }
        });

        //暂停
        pauseButton.setOnAction(e ->
        {
            timerController.getAnimation().stop();
            gamePane.conBrickController.setMove(false);
            if(timerController.getTest() != null)
                timerController.getTest().getAnimation().stop();
            for(int i = 0 ; i < 3; i++){
                if(gamePane.ballNum[i])
                    gamePane.ballControllers[i].getAnimation().stop();
            }
        });

        //重新开始
        restarButton.setOnAction(e ->
        {
            timerController.getAnimation().stop();
            gamePane.conBrickController.setMove(false);
            for(int i = 0 ; i < 3; i++){
                if(gamePane.ballNum[i])
                    gamePane.ballControllers[i].getAnimation().stop();
            }

            gamePane.getChildren().clear();
            gamePane = null;

            gamePane = new GamePane(this);
            gamePane.setGameOverView(gameOverView);
            setCenter(gamePane);
            gamePane.requestFocus();

            gamePane.setReopen(true);
            pointController.setPoint(0);
            timerController.resetTimer();

            gamePane.reopenGame();
        });
        buttonPane.getChildren().add(startButton);
        buttonPane.getChildren().add(pauseButton);
        buttonPane.getChildren().add(restarButton);

    }

    /**
     * 时间分数初始化
     */
    public void IniInfoPane()
    {
        infoPane.getChildren().add(timerController.getView());
        infoPane.getChildren().add(pointController.getView());
//        timerController.startTimer();
    }

    /**
     * 调用结束前终止所有动画
     */
    public void stopAll(){
        timerController.getAnimation().stop();
        gamePane.conBrickController.setMove(false);
        if(timerController.getTest() != null)
            timerController.getTest().getAnimation().stop();
        for(int i = 0 ; i < 3; i++){
            if(gamePane.ballNum[i])
                gamePane.ballControllers[i].getAnimation().stop();
        }
    }

    public void startTimer()
    {
        timerController.startTimer();
    }

    public void setGamePane(GamePane gamePane) {
        this.gamePane = gamePane;
    }
}
