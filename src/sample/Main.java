package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {
Button button;
Button button2;
Scene scene1;
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        registers.getInstance().readAll();
        button = new Button("Log in");
        button.setOnAction(e-> login.display());
        button2 = new Button("register");
        button2.setOnAction(e-> registration.display());
        StackPane layout2 = new StackPane();
        HBox layout1 = new HBox(20);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll( button,button2);
        layout2.getChildren().add(layout1);
        scene1 = new Scene(layout2, 400,400);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Home");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
