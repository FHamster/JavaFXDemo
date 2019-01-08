package sample.myUtil;

import javafx.scene.media.Media;

import java.io.File;

public class MediaLoader
{
    public static Media bgm = new Media(new File("Resources/media/bgm.mp3").toURI().toString());
    public static Media hit = new Media(new File("Resources/media/hit.wav").toURI().toString());
    public static Media death = new Media(new File("Resources/media/death.wav").toURI().toString());



}
