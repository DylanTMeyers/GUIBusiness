package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;


public class registration {
    static Stage windows;
    static TextField firstName;
    static TextField lastName;
    static TextField middleInitial;
    static TextField email;
    static PasswordField  password;
    static CheckBox box1;
    static GridPane grid;
    static CheckBox box2;
    static ComboBox<String> comboBox;
    public static void display() {

        windows = new Stage();
        HBox hbox = new HBox(10);
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));


        firstName = new TextField();
        firstName.setPromptText("first name");
        Label label = new Label("First Name:");
        GridPane.setConstraints(label,0,0);
        GridPane.setConstraints(firstName,1,0);

        lastName = new TextField();
        lastName.setPromptText("last name");
        Label label2 = new Label("Last Name:");
        GridPane.setConstraints(label2,0,1);
        GridPane.setConstraints(lastName,1,1);

        middleInitial = new TextField();
        middleInitial.setPromptText("middle initial");
        Label label3 = new Label("Middle Initial: (optional)");
        GridPane.setConstraints(label3,0,2);
        GridPane.setConstraints(middleInitial,1,2);

        email = new TextField();
        email.setPromptText("email");
        Label label4 = new Label("Email:");
        GridPane.setConstraints(label4,0,3);
        GridPane.setConstraints(email,1,3);

        password = new PasswordField();
        password.setPromptText("password");
        Label label5 = new Label("Password:");
        GridPane.setConstraints(label5,0,4);
        GridPane.setConstraints(password,1,4);

        box1 = new CheckBox("Male");
        box2 = new CheckBox("Female");
        Label label6 = new Label("Gender:");
        hbox.getChildren().addAll(box1,box2);
        GridPane.setConstraints(hbox,1,5);
        GridPane.setConstraints(label6,0,5);
        grid.addRow(6, new Text(""));
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("American Indian or Alaska Native","Asian","Black or African American","Hispanic or Latino","Native Hawaiian or Other Pacific Islander","White or Caucasian");
        GridPane.setConstraints(comboBox,1,7);
        Label label7 = new Label("Race:");
        GridPane.setConstraints(label7,0,7);
        Button button2 = new Button("register");
        button2.setOnAction(e-> {
            try {
                register();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        for(int i =8; i<11; i++)
        grid.addRow(i, new Text(""));

        GridPane.setConstraints(button2,0,11);



        grid.getChildren().addAll(firstName,label,lastName,label2,middleInitial,label3,email,label4,password,label5,hbox,button2,comboBox,label6,label7);

        Scene scene = new Scene(grid, 600,600);
        windows.setScene(scene);
        windows.show();

    }
    private static void register() throws IOException {
        if( comboBox.getValue() != null && !firstName.getText().isEmpty() &&!lastName.getText().isEmpty()  && !email.getText().isEmpty() && !password.getText().isEmpty() &&(box1.isSelected() ||box2.isSelected())){
            if(!registers.getInstance().alreadyReg(email.getText())) {
                write();
                windows.close();
            }
            else{
                Label label = new Label("This email has already registered!");
                GridPane.setConstraints(label,0,12);
                grid.getChildren().add(label);
            }
        }
        else{
            Label label = new Label("Fill out every detail!");
            GridPane.setConstraints(label,0,12);
            grid.getChildren().add(label);
        }
    }
    private static void write() throws IOException {

        Persons persons = new Persons(firstName.getText(),lastName.getText(),email.getText(),middleInitial.getText(),password.getText(),comboBox.getValue());
        if(box1.isSelected()){
            persons.setGender(box1.getText());

        }
        else{
            persons.setGender(box2.getText());
        }
        registers.getInstance().addPeople(persons);
        registers.getInstance().writeAll();
    }
    }