package sample;

import javafx.scene.layout.AnchorPane;

public class RootPane extends AnchorPane
{
    GamePane gamePane = new GamePane();

    public RootPane()
    {
        getChildren().add(gamePane);
        setLeftAnchor(gamePane, 0.0);
    }

    public GamePane getGamePane()
    {
        return gamePane;
    }
}
