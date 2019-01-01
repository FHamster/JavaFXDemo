package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sample.infoPane.controller.TimerController;

public class RootPane extends BorderPane
{
    GamePane gamePane = new GamePane(this);
    InfoPane infoPane = new InfoPane();
    ButtonPane buttonPane = new ButtonPane();

    //控制器
    TimerController timerController = new TimerController(gamePane);

    //按键
    Button startButton = new Button("开始");
    Button pauseButton = new Button("暂停");
    Button stopButton = new Button("停止");
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

        });

        pauseButton.setOnAction(e ->
        {

        });

        stopButton.setOnAction(e ->
        {

        });

        restarButton.setOnAction(e ->
        {

        });
        buttonPane.getChildren().add(startButton);
        buttonPane.getChildren().add(pauseButton);
        buttonPane.getChildren().add(stopButton);
        buttonPane.getChildren().add(restarButton);

    }

    public void IniInfoPane()
    {
        infoPane.getChildren().add(timerController.getView());
        timerController.startTimer();
    }


}
