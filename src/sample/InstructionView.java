package sample;

import javafx.scene.Node;
import javafx.scene.text.Text;

import java.awt.*;

import static javafx.scene.text.Font.*;

/**
 * 用于解释说明
 */
public class InstructionView  extends Text {


    public InstructionView(){
        this.setText("操作指南：\n        按空格键发射,可以使用\n鼠标或者键盘控制，W,S,A,\nD 分别对应上下左右.\n" +
                "        非常不好意思的是，由于\n技术问题，目前只有一条命，\n珍爱生命，加油吧，骚年!!!");
    }

}
