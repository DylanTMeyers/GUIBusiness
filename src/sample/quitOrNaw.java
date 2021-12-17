package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class quitOrNaw {
    static Stage windows;
    static boolean answer;
    static Scene scenes1;
    public static boolean display(){
        windows = new Stage();
        StackPane layout = new StackPane();
        Button yesButton = new Button("yes");
        layout.prefHeight(300);
        layout.prefWidth(200);
        yesButton.setOnAction(e->{
            answer = true;
            windows.close();
        });
        Button noButton = new Button("no");

        noButton.setOnAction(e->{
            answer = false;
            windows.close();
        });
        HBox hbox = new HBox();
        VBox vbox2 = new VBox();
        hbox.setSpacing(10.0);
        Label label1 = new Label("Are you sure you would like to close the window?");
        Label label2 = new Label();
        hbox.getChildren().addAll(yesButton,noButton);
        hbox.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(hbox);
        vbox2.getChildren().addAll(label1,label2,layout);
        scenes1 = new Scene(vbox2,300,100);

        windows.setScene(scenes1);
        windows.showAndWait();
        return answer;
    }
}
