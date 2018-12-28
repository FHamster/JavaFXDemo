package sample;

import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane
{
    GamePane gamePane = new GamePane();
    InfoPane infoPane = new InfoPane();

    public RootPane()
    {
//        getChildren().add(gamePane);
        setCenter(gamePane);
        setRight(infoPane);
//        getChildren().add(infoPane);
//        setLeftAnchor(gamePane, 0.0);

    }

    public GamePane getGamePane()
    {
        return gamePane;
    }
}
