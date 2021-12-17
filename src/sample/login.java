package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class login {
    static Stage windows;
    static GridPane grid;
    static TextField user;
    static PasswordField pass;

    public static void display() {
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(10);
        windows = new Stage();
        pass = new PasswordField();
        pass.setPromptText("password..");
        GridPane.setConstraints(pass,1,1);
        user = new TextField();
        user.setPromptText("email..");
        GridPane.setConstraints(user,1,0);
        Label nameLabel = new Label("email:");
        GridPane.setConstraints(nameLabel,0,0);

        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel,0,1);



        Button button = new Button("login");
        GridPane.setConstraints(button,1,2);

        button.setOnAction(e->logins());
        grid.getChildren().addAll(nameLabel,passLabel,user,pass,button);
        Scene scene = new Scene(grid,300,300);
        windows.setScene(scene);
        windows.show();
    }
    private static void logins(){

        if(registers.getInstance().login(user.getText(), pass.getText())){
            loggedin.display(registers.getInstance().loggedIn(user.getText(),pass.getText()));
      }
        else{
            Label label = new Label("Wrong password and/or email");
           GridPane.setConstraints(label,1,3);
          grid.getChildren().add(label);
        }
    }
}