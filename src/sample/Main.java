package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
//        Parent root = FXMLLoader.load(getClass().getResource("../fxml/RootPane.fxml"));
//        RootPane rootPane = new RootPane();
        RootPaneController root = new RootPaneController();
//        root.setext
        primaryStage.setTitle("Brick Breaker");
        primaryStage.setScene(new Scene(root, 900, 800));
        primaryStage.show();

//        root.requestFocus();
        root.gamePane.requestFocus();

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
