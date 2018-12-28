package sample;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.infoPane.controller.TimerController;

public class InfoPane extends VBox
{
    TimerController timerController = new TimerController();

    public InfoPane()
    {
//        setMaxSize(100, 100);
//        setMinSize(100, 100);
//        setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));

        getChildren().add(0, timerController.getView());
        timerController.startTimer();

    }
}
