package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Brick Breaker");
        RootPane rootPane = new RootPane();

        primaryStage.setScene(new Scene(rootPane, 800, 800));
        primaryStage.show();

        rootPane.gamePane.requestFocus();

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
