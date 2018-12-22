package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        GamePane gamePane = new GamePane();
//        gamePane.setOnKeyPressed(e -> System.out.println(3));
        primaryStage.setScene(new Scene(gamePane, 300, 275));
        primaryStage.show();

        gamePane.requestFocus();

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
