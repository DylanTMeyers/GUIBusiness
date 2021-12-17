package sample;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Businesses {
    static Stage primaryStage;

    static TreeView<String> tree;

    public static void display(String email) {
        primaryStage = new Stage();

        Business business = new Business();
        BorderPane layout = new BorderPane();
        primaryStage.setTitle(email+ "'s Business");
        TreeItem<String> root, Info_Lists, Creditors, Products, Employee_Info, Statistics, Graphs;

        root = new TreeItem<>();
        root.setExpanded(false);
        Info_Lists = makeBranch("Info Lists",root);
//        makeBranch("Investors List",Info_Lists);
        makeBranch("Products List", Info_Lists);
        makeBranch("Creditors List", Info_Lists);
        makeBranch("All Transactions List",Info_Lists);

        Creditors = makeBranch("Creditors",root);
        makeBranch("Subtract Credit",Creditors);
        makeBranch("Add Credit", Creditors);

        Employee_Info = makeBranch("Employee Info",root);
        makeBranch("Search Employee",Employee_Info);
        makeBranch("Employee Taxes", Employee_Info);
        makeBranch("Add Employee",Employee_Info);
        makeBranch("Add Hours", Employee_Info);

        Products = makeBranch("Products",root);
        makeBranch("Restock",Products);
        makeBranch("Item Sold", Products);
        makeBranch("Inventory Depletion",Products);

        Statistics = makeBranch("Statisics",root);
        makeBranch("Daily Stats",Statistics);
        makeBranch("Weekly Stats", Statistics);
        makeBranch("Monthly Stats", Statistics);
        makeBranch("Yearly Stats",Statistics);

        Graphs = makeBranch("Graphs",root);
        makeBranch("Daily Graph",Graphs);
        makeBranch("Weekly Graph", Graphs);
        makeBranch("Monthly Graph", Graphs);

        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue)->{
            if(newValue != null) {
                try {
                    business.display(newValue.getValue(),email.toLowerCase());
                } catch (IOException | InterruptedException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        Button button = new Button("profile");
        button.setOnAction( e-> {
            primaryStage.close();
            loggedin.display(registers.getInstance().loggedIn(login.user.getText(),login.pass.getText()));
        });
        layout.setTop(button);
        layout.setCenter(tree);

        primaryStage.setScene(new Scene(layout, 400, 400));
        primaryStage.show();
    }
    public static TreeItem<String> makeBranch(String title, TreeItem<String>parent){
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(false);
        parent.getChildren().add(item);
        return item;
    }
}