package sample;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sample.infoPane.controller.TimerController;

public class RootPane extends BorderPane {
    GamePane gamePane = new GamePane(this);
    InfoPane infoPane = new InfoPane();

    TimerController timerController = new TimerController(gamePane);

    public RootPane() {
        //设置布局
        setCenter(gamePane);
        setRight(infoPane);

        //初始化
        IniGamePane();
        IniInfoPane();

    }

    public void IniGamePane() {
    }

    public void IniInfoPane() {
        infoPane.getChildren().add(timerController.getView());
        timerController.startTimer();
    }


}
