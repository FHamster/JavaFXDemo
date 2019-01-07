package sample;

import javafx.animation.Animation;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sample.infoPane.controller.PointController;
import sample.infoPane.controller.TimerController;

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
//    Button stopButton = new Button("停止");
    Button restarButton = new Button("重玩");

    public RootPane()
    {
        //pane初始化
        IniGamePane();
        IniInfoPane();
        IniButtonPane();

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

//        stopButton.setOnAction(e ->
//        {
//
//        });

        restarButton.setOnAction(e ->
        {
            gamePane.setReopen(true);
            gamePane.reopenGame();
        });
        buttonPane.getChildren().add(startButton);
        buttonPane.getChildren().add(pauseButton);
//        buttonPane.getChildren().add(stopButton);
        buttonPane.getChildren().add(restarButton);

    }

    public void IniInfoPane()
    {
        infoPane.getChildren().add(timerController.getView());
        infoPane.getChildren().add(pointController.getView());
        timerController.startTimer();
    }

    public void setGamePane(GamePane gamePane) {
        this.gamePane = gamePane;
    }
}
