package sample;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class loggedin {
    static Stage windows;
   static boolean answer = false;

public static void display(Persons persons){
    Button button = new Button("Business Stats");
    button.setOnAction(e-> {
        windows.close();
        Businesses.display(persons.getEmail());
    });
    VBox p = new VBox();
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10,10,10,10));
    grid.setVgap(10);
    grid.setHgap(10);
  windows = new Stage();
  windows.initModality(Modality.APPLICATION_MODAL);
  windows.setTitle(persons.getFirstName() +"'s Profile");
  windows.setOnCloseRequest(e->{
      e.consume();
      closeProgram();
  });
  windows.setMinWidth(300);
  Label label = new Label("First Name:");
    GridPane.setConstraints(label,0,0);
  Label labels = new Label(persons.getFirstName());
    GridPane.setConstraints(labels,1,0);
  Label label1 = new Label("Last Name:");
    GridPane.setConstraints(label1,0,1);
  Label labels1 = new Label(persons.getLastName());
    GridPane.setConstraints(labels1,1,1);
  Label label2 = new Label("Middle Initial:");
    GridPane.setConstraints(label2,0,2);
  Label labels2 = new Label(persons.getMiddleInitial());
  GridPane.setConstraints(labels2,1,2);
  Label label3 = new Label("Email:");
    GridPane.setConstraints(label3,0,3);
    Label labels3 = new Label(persons.getEmail());
    GridPane.setConstraints(labels3,1,3);
  Label label4 = new Label("Password:");
    GridPane.setConstraints(label4,0,4);
    Label labels4 = new Label(persons.getPassword());
    GridPane.setConstraints(labels4,1,4);
  Label label5 = new Label("Gender:");
    GridPane.setConstraints(label5,0,5);
  Label labels5 = new Label(persons.getGender());
    GridPane.setConstraints(labels5,1,5);
  Label label6 = new Label("Race:");
    GridPane.setConstraints(label6,0,6);
  Label labels6 = new Label(persons.getRace());
    GridPane.setConstraints(labels6,1,6);

    Button doneButton = new Button("done");
    doneButton.setOnAction(e->{
        login.windows.close();
        windows.close();
    });
    GridPane.setConstraints(doneButton,1,8);
    grid.getChildren().addAll(label,labels,labels1,label1, labels2,label2,labels3,label3,labels4,label4,labels5,label5,labels6,label6,doneButton);
    p.getChildren().addAll(button,grid);
    Scene scene = new Scene(p, 300,300);
    windows.setScene(scene);

  windows.showAndWait();
}
private static void closeProgram(){
    answer = quitOrNaw.display();
if(answer){
    windows.close();
}
}
}
