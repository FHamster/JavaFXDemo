package sample;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sample.infoPane.controller.TimerController;

public class RootPane extends BorderPane
{
    private GamePane gamePane = new GamePane(this);
    private InfoPane infoPane;

    TimerController timerController = new TimerController();

    public RootPane()
    {
        infoPane = new InfoPane(timerController.getView());
//        getChildren().add(gamePane);
        setCenter(gamePane);
        setRight(infoPane);

        timerController.startTimer();
//        getChildren().add(infoPane);
//        setLeftAnchor(gamePane, 0.0);

    }

    public GamePane getGamePane()
    {
        return gamePane;
    }

    public TimerController getTimerController()
    {
        return timerController;
    }
}
