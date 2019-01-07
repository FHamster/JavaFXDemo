package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.infoPane.controller.PointController;
import sample.infoPane.controller.TimerController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootPaneController extends BorderPane implements Initializable
{
    GamePane gamePane;

    //region 按键配置region
    @FXML
    private Button restartButton;

    @FXML
    public void startButtonAction(ActionEvent event)
    {
        System.out.println("startButtonAction");
        timerController.startTimer();
        gamePane.conBrickController.setMove(true);
        for(int i = 0 ; i < 3; i++){
            if(gamePane.ballNum[i])
                gamePane.ballControllers[i].start();
        }
    }

    @FXML
    private Button startButton;

    @FXML
    public void pauseButtonAction(ActionEvent event)
    {

        System.out.println("pauseButtonAction");
        timerController.getAnimation().stop();
        gamePane.conBrickController.setMove(false);
        for(int i = 0 ; i < 3; i++){
            if(gamePane.ballNum[i])
                gamePane.ballControllers[i].getAnimation().stop();
        }
    }

    @FXML
    private Button pauseButton;

    public void restartButtonAction(ActionEvent event)
    {
        System.out.println("restartButtonAction");
        gamePane.getChildren().removeAll();
        gamePane = null;

//        gamePane = new GamePane(this);

//        gamePane.setReopen(true);
//        gamePane.reopenGame();
    }

    //endregion

    //region 信息面板配置region
    @FXML
    private Text TimerText;
    @FXML
    private Text PointText;

    //控制器
    TimerController timerController = new TimerController(this);
    PointController pointController = new PointController(this);

    public void initiaInfoPane()
    {
        PointText.textProperty().bind(pointController.pointStrProperty());
        TimerText.textProperty().bind(timerController.timerStrProperty());
        timerController.startTimer();
    }
    //endregion


    public RootPaneController()
    {
        loadFxml();
        //pane初始化
        IniGamePane();
        setCenter(gamePane);
    }


    private void loadFxml()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/RootPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void IniGamePane()
    {
    }

    public void setGamePane(GamePane gamePane)
    {
        this.gamePane = gamePane;
    }

    public GamePane getGamePane()
    {
        return gamePane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initiaInfoPane();
        gamePane = new GamePane(this);
    }

}
