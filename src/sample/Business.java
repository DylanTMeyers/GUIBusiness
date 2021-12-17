package sample;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

public class Business {
    static Stage windows;
    static Finance theFinance;
    static TextField productName;
    static TextField subNumbers;
    static TextField name1;
    static TextField prices;
    static double productAmount;
    static double prodPrice;
    static boolean productQuaExists;
    static String[] reupProduct;
    static boolean reupProductExist;
    static ArrayList<String> productsOnFile;
    static TIMEGOD timegod;
    static PrintWriter out;
    static double price2;
    static String[] StringArray;
    static double subNumber;
    static double price;
    static Label label19;
    static TextField prod;
    static double prodAmount;
    static Label label21;
    static String email;
    public void display(String choice,String email) throws IOException, InvocationTargetException, InterruptedException {
       File prods  =new File(email+"businessProduct.txt");
       if(!prods.exists()) {
           prods.createNewFile();
       }
        File cred = new File(email+"creditor.txt");
        if(!cred.exists()) {
            cred.createNewFile();
        }
        new File(email+"Employees.txt");
        File emp = new File(email+"creditor.txt");
        if(!emp.exists()) {
            emp.createNewFile();
        }
        File inv = new File(email+"investorsBusinessProduct.txt");
        if(!inv.exists()) {
            inv.createNewFile();
        }
        File time =new File(email+"TimeGod.txt");
        if(!time.exists()) {
            time.createNewFile();
        }
        File tax = new File(email+"Taxes.txt");
        if(!tax.exists()) {
            tax.createNewFile();
        }
        price2 = 0.0;
        Business.email = email;
        boolean notSwitch =false;
        String label="";
        productsOnFile = new ArrayList<>(10);
        ArrayList<String> investorProduct = new ArrayList<>(10);
        PrintWriter cool;
        theFinance = new Finance(email);
        timegod = new TIMEGOD(email);
        timegod.readAll();
        File myObj = new File(email+"businessProduct.txt");
        Scanner hey = new Scanner(myObj);
        File myOb = new File(email+"investorsBusinessProduct.txt");
        Scanner naw = new Scanner(myOb);
        Employees.getInstance().readAll();
        while(hey.hasNextLine()){
            productsOnFile.add(hey.nextLine());
        }
        if(productsOnFile.size()>0) {
            price2 = Double.parseDouble(productsOnFile.get(productsOnFile.size() - 1));
        }
        else{
            price2 = 0;
        }
        while(naw.hasNextLine()){
            investorProduct.add(naw.nextLine());
        }
            switch (choice) {
                        case "Investors List":
                            label = label + "\nINVESTOR LIST:";
                            if(investorProduct.size()==0){
                                label = label + "\nNo investors.";
                            }
                            for (String s : investorProduct) {
                                label = label + "\n" + s;
                            }
                            break;
                        case "Products List":
                            try {
                               label = label + "\nCurrent Product List:";
                                label = label + "\n----------------------";
                                label = label + "\n";
                                label = label + "\n";
                                label = label + "\nProduct - Quantity";
                                label = label +"\n----------------------";
                                for (int I = 0; productsOnFile.size() - 1 > I; I++) {
                                    String[] product = productsOnFile.get(I).split("   ");
                                    label = label + "\n"+ product[0] + "  -  " +product[1] + " units";
                                }
                                label = label + "\n----------------------";
                                label = label + "\nTOTAL SALES: $" + productsOnFile.get(productsOnFile.size() - 1);
                            } catch (IndexOutOfBoundsException e) {
                                label = label + "\nThere are no products in the product list (add some first)";
                            }
                            break;
                        case "Creditors List":
                            label = theFinance.printList();
                            break;
                        case "All Transactions List":
                            label = timegod.printAll();
                            break;
                        case "Subtract Credit":
                            VBox vbox5 = new VBox();
                            Button button4 = new Button("Subtract Credit");
                            String sub = "\nTHE CURRENT CRED LIST:";
                            sub = sub + theFinance.printList();
                            sub = sub + "\nWhat is the name of the creditor?";
                            Label label7 = new Label(sub);
                            TextField credName = new TextField();
                            Label label9= new Label("\nWhat is the number you would like to subtract?");
                            TextField credSub = new TextField();
                            Label label8= new Label();
                            button4.setOnAction(e-> {
                               boolean Person = theFinance.subCreditors(credName.getText(), Integer.parseInt(credSub.getText()));
                                try {
                                    theFinance.fileSet();
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                                if(Person) {
                                    String newCred = "\nTHE CURRENT CRED LIST:";
                                    newCred = newCred + theFinance.printList();
                                    newCred = newCred + "\nWhat is the name of the creditor?";
                                    label7.setText(newCred);
                                }
                                else{
                                    label8.setText(credName.getText() + " is not a documented creditor.");
                                }
                                    });
                            vbox5.getChildren().addAll(label7,credName,label9,credSub,button4,label8);
                            Scene scene4 = new Scene(vbox5, 300, 300);
                            windows = new Stage();
                            windows.setScene(scene4);
                            windows.show();
                            notSwitch = true;
                            label = "";
                            break;
                        case "Add Credit":
                            VBox vbox6 = new VBox();
                            Button button5 = new Button("Add Credit");
                            String add = "\nTHE CURRENT CRED LIST:";
                            add = add + theFinance.printList();
                            add = add + "\nWhat is the name of the creditor?";
                            Label label10 = new Label(add);
                            TextField credName2 = new TextField();
                            Label label11= new Label("\nWhat is the number you would like to add?");
                            TextField credAdd = new TextField();
                            Label label12= new Label();
                            button5.setOnAction(e-> {
                               theFinance.addCreditors(credName2.getText(), Integer.parseInt(credAdd.getText()));
                                try {
                                    theFinance.fileSet();
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                                    String newCred = "\nTHE CURRENT CRED LIST:";
                                    newCred = newCred + theFinance.printList();
                                    newCred = newCred + "\nWhat is the name of the creditor?";
                                    label10.setText(newCred);

                            });
                            vbox6.getChildren().addAll(label10,credName2,label11,credAdd,button5,label12);
                            Scene scene5 = new Scene(vbox6, 300, 300);
                            windows = new Stage();
                            windows.setScene(scene5);
                            windows.show();
                            notSwitch = true;
                            label = "";
                            break;
                        case "Restock":
                            VBox vbox7 = new VBox();
                            Button button6 = new Button("Add Product");
                            String products = "";
                                for (int I = 0; productsOnFile.size() - 1 > I; I++) {
                                    products = products + "\n" + productsOnFile.get(I);
                                }
                                products = products + "\nWhat is the product name?";
                                Label label14 = new Label(products);
                                productName = new TextField();
                                Label label13 = new Label("What is the amount of product?");
                                TextField productAmounts = new TextField();
                                Label label15 = new Label("How much did you buy it for?");
                                TextField prodPrices = new TextField();
                            button6.setOnAction(e-> {
                                try {
                                    reupProductExist = false;
                                    productAmount = Double.parseDouble(productAmounts.getText());
                                    prodPrice = Double.parseDouble(prodPrices.getText());
                                    Restock();
                                    String products2 = "";
                                    for (int I = 0; productsOnFile.size() - 1 > I; I++) {
                                        products2 = products2 + "\n" + productsOnFile.get(I);
                                    }
                                    products2 = products2 + "\nWhat is the product name?";
                                    label14.setText(products2);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            });
                            vbox7.getChildren().addAll(label14,productName,label13,productAmounts,label15,prodPrices,button6);
                            Scene scene6 = new Scene(vbox7, 300, 300);
                            windows = new Stage();
                            windows.setScene(scene6);
                            windows.show();
                            notSwitch = true;
                            label = "";
                            break;
                        case "Item Sold":
                                VBox vbox8 = new VBox();
                                Button button7 = new Button("Sold Product");
                                productQuaExists = true;
                                String products1 ="";
                                products1 = products1 + "\n" + "LIST OF PRODUCTS:";
                                for (int I = 0; productsOnFile.size() - 1 > I; I++) {
                                    products1 = products1 + "\n" + productsOnFile.get(I);
                                }
                                products1 = products1 + "\n$" + price2;
                                products1 = products1 + "\nWhat is the name of the product you sold?";
                                Label label18 = new Label(products1);
                                name1 = new TextField();
                                Label label16 = new Label("How much product did you sell?");
                                subNumbers = new TextField();
                                Label label17 = new Label("how much did this product sell for?");
                                prices = new TextField();
                                label19 = new Label();

                            button7.setOnAction(e-> {
                                try {
                                    subNumber = Double.parseDouble(subNumbers.getText());
                                    price = Double.parseDouble(prices.getText());
                                    productQuaExists = true;
                                    sold();
                                    String products3 = "";
                                    products3 = products3 + "\n" + "LIST OF PRODUCTS:";
                                    for (int I = 0; productsOnFile.size() - 1 > I; I++) {
                                        products3 = products3 + "\n" + productsOnFile.get(I);
                                    }
                                    products3 = products3 + "\n$" + price2;
                                    products3 = products3 + "\nWhat is the name of the product you sold?";
                                    label18.setText(products3);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            });
                            vbox8.getChildren().addAll(label18,name1,label16,subNumbers,label17,prices,button7,label19);
                            Scene scene7 = new Scene(vbox8, 300, 300);
                            windows = new Stage();
                            windows.setScene(scene7);
                            windows.show();
                            notSwitch = true;
                            label = "";
                            break;

                        case "Inventory Depletion":
                            VBox vbox9 = new VBox();
                            Button button8 = new Button("Deplete inventory");
                            productQuaExists = true;
                            String products2 ="";
                            products2 = products2 + "\n" + "LIST OF PRODUCTS:";
                            for (int I = 0; productsOnFile.size() - 1 > I; I++) {
                                products2 = products2 + "\n" + productsOnFile.get(I);
                            }
                            products2 = products2 + "\n$" + price2;
                            products2 = products2 + "\nWhat did you use?";
                            Label label19 = new Label(products2);
                            prod = new TextField();
                            Label label20 = new Label("how much did you consume?");
                            subNumbers = new TextField();
                            label21 = new Label();
                            button8.setOnAction(e-> {
                                subNumber = Double.parseDouble(subNumbers.getText());
                                prodAmount = Double.parseDouble(subNumbers.getText());
                                productQuaExists = true;
                                String products3 = "";
                                try {
                                    deplete();
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                                products3 = products3 + "\n" + "LIST OF PRODUCTS:";
                                for (int I = 0; productsOnFile.size() - 1 > I; I++) {
                                    products3 = products3 + "\n" + productsOnFile.get(I);
                                }
                                products3 = products3 + "\n$" + price2;
                                products3 = products3 + "\nWhat did you use?";
                                label19.setText(products3);

                            });
                            vbox9.getChildren().addAll(label19,prod,label20,subNumbers,button8,label21);
                            Scene scene8 = new Scene(vbox9, 300, 300);
                            windows = new Stage();
                            windows.setScene(scene8);
                            windows.show();
                            notSwitch = true;
                            label = "";
                            break;
                        case "Search Employee":
                            VBox vbox4 = new VBox();
                            String all = "";
                            all = all + "\nCurrent Employee list:";
                            all = all + "\n-----------------------";
                            all = all + Employees.getInstance().printAllEmployees();
                            all = all + "\n";
                            all = all + "\nWhat is the name of the employee?";
                            Label alls = new Label(all);
                            TextField names4 = new TextField();
                            Button button3 = new Button("Look up Employee");
                            Label extra = new Label();
                            vbox4.getChildren().addAll(alls, names4,button3,extra);
                            button3.setOnAction(e-> {
                                        if (Employees.getInstance().getEmployee(names4.getText()) == null) {
                                            extra.setText("\nThis employee does not exist in the database!");
                                        } else {
                                            extra.setText("\n" + Employees.getInstance().getEmployee(names4.getText()).toString());
                                        }
                                        names4.clear();
                                    });
                            Scene scene3 = new Scene(vbox4, 300, 300);
                            windows = new Stage();
                            windows.setScene(scene3);
                            windows.show();
                            notSwitch = true;
                            label = "";
                            break;
                        case "Employee Taxes":
                            VBox vbox3 = new VBox();

                            String las2 = "";
                           las2= las2 + "\nCurrent Employee list:";
                            las2= las2 + "\n-----------------------";
                            las2 = las2 + Employees.getInstance().printAllEmployees();
                            las2= las2 +  "\n";
                            las2= las2 + "\nWhat is the name of the employee?";
                            Label label5 = new Label(las2);
                            TextField names3 = new TextField();
                            Button button2 = new Button("Make tax form");
                            final String[] found = new String[1];
                            Label label6 = new Label(found[0]);
                            vbox3.getChildren().addAll(label5, names3,button2,label6);
                            button2.setOnAction(e-> {
                                found[0] = taxes.lookup(names3.getText(),email);
                                label6.setText(found[0]);
                                names3.clear();

                            });
                            Scene scene2 = new Scene(vbox3, 300, 300);
                            windows = new Stage();
                            windows.setScene(scene2);
                            windows.show();
                            notSwitch = true;
                            label = "";
                            break;
                        case "Add Employee":
                            VBox vbox2 = new VBox();
                            Label label1 = new Label("What is the name of the employee?");
                            TextField names2 = new TextField();
                            Label labels1 = new Label("What is the ID of the employee?");
                            TextField id = new TextField();
                            Label labels = new Label("How many hours did the employee work?");
                            TextField hourWorked = new TextField();
                            Label labels2 = new Label("How much does the employee make per hour?");
                            TextField hourlyPay = new TextField();
                            Label labels3 = new Label("Is the employee active or non-active?");
                            TextField status = new TextField();
                            Label labels4 = new Label("What position does the employee hold?");
                            TextField position = new TextField();
                            Label added = new Label();
                            Button button1 = new Button("add hours");
                            vbox2.getChildren().addAll(label1, names2, labels1, id, labels, hourWorked, labels2, hourlyPay, labels3, status, labels4, position,added,button1);
                            button1.setOnAction(e-> {
                                        Employee employee = new Employee(Integer.parseInt(id.getText()), names2.getText(), Double.parseDouble(hourWorked.getText()), Double.parseDouble(hourlyPay.getText()), status.getText(), position.getText());
                                        Employees.getInstance().add(names2.getText(), employee);
                                        names2.clear();
                                        id.clear();
                                        hourWorked.clear();
                                        hourlyPay.clear();
                                        status.clear();
                                        position.clear();
                                try {
                                    Employees.getInstance().writeAll();
                                    added.setText("Employee added");
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            });
                            Scene scene1 = new Scene(vbox2, 300, 300);
                            windows = new Stage();
                            windows.setScene(scene1);
                            windows.show();
                            notSwitch = true;
                            label = "";
                            break;
                        case "Add Hours":
                            VBox vbox = new VBox();
                            String la = "";
                            String la2 = "";
                            la = la + "\nCurrent Employee list:";
                            la = la + "\n-----------------------";
                            la = la + Employees.getInstance().printAllEmployees();
                            la = la + "\n";
                            la = la + "\nWhat is the name of the employee?";
                            Label label2 =  new Label(la);
                            vbox.getChildren().add(label2);
                            TextField names = new TextField();
                            vbox.getChildren().add(names);
                            la2 = la2 +"\nHow many more hours did they work?";
                            Label label3 =  new Label(la2);
                            vbox.getChildren().add(label3);
                            TextField additionalHours = new TextField();
                            vbox.getChildren().add(additionalHours);
                            Button button = new Button("add hours");
                            Label label4 = new Label();
                            button.setOnAction(e->{
                                if( Employees.getInstance().getEmployee(names.getText()) != null) {
                                    try{
                                        Employees.getInstance().getEmployee(names.getText()).addHoursWorked(Integer.parseInt(additionalHours.getText()));
                                        label4.setText("Hours successfully added");
                                    }catch(NumberFormatException d){
                                        label4.setText("You have to put an integer for quantity of hours added!");
                                    }
                                }
                                else{
                                    label4.setText("Name not found!");

                                }
                                additionalHours.clear();
                                names.clear();
                            });
                            vbox.getChildren().add(button);
                            vbox.getChildren().add(label4);
                            Scene scene = new Scene(vbox, 300, 300);
                            windows = new Stage();
                            windows.setScene(scene);
                            windows.show();
                            notSwitch = true;
                            label = "";
                            break;
                        case "Daily Stats":
                            label = timegod.todayReport();
                            break;
                        case "Weekly Stats":
                            label = timegod.weekReport();
                            break;
                        case "Monthly Stats":
                           label =  timegod.monthReport();
                            break;
                        case "Yearly Stats":
                           label =  timegod.yearReport();
                            break;
                        case "Daily Graph":
                            int[] data = new int[2];
                            data[0] = (int) timegod.thatDayReports(timegod.today()-1).getProfit();
                            data[1] = (int) timegod.thatDayReports(timegod.today()).getProfit();
                            int MAX = (data[0] + data[1]);
                            SwingUtilities.invokeAndWait(() -> {
                                var panel = new BarGraph(data,"yesterday","today",MAX);
                                panel.setBackground(Color.GREEN.darker());
                                var frame = new JFrame("A simple graphics program");
                                frame.setSize(400, 300);
                                frame.getContentPane().add(panel, BorderLayout.CENTER);
                                frame.setVisible(true);
                                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            });
                            label = "";
                            notSwitch = true;
                            break;
                        case "Weekly Graph":
                            data = new int[2];
                            data[0] = (int) timegod.getLastWeek();
                            data[1] = (int) timegod.getThisWeek();
                            MAX = (data[0] + data[1]);
                            SwingUtilities.invokeAndWait(() -> {
                                var panel = new BarGraph(data,"Last week","This week",MAX);
                                panel.setBackground(Color.GREEN.darker());
                                var frame = new JFrame("A simple graphics program");
                                frame.setSize(400, 300);
                                frame.getContentPane().add(panel, BorderLayout.CENTER);
                                frame.setVisible(true);
                                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                            });
                            label = "";
                            notSwitch = true;
                            break;
                        case "Monthly Graph":
                            data = new int[2];
                            data[0] = (int) timegod.getLastMonth();
                            data[1] = (int) timegod.getThisMonth();
                            MAX = (data[0] + data[1]);
                            SwingUtilities.invokeAndWait(() -> {
                                var panel = new BarGraph(data,"Last month","This month",MAX);
                                panel.setBackground(Color.GREEN.darker());
                                var frame = new JFrame("A simple graphics program");
                                frame.setSize(400, 300);
                                frame.getContentPane().add(panel, BorderLayout.CENTER);
                                frame.setVisible(true);
                                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            });
                            label = "";
                            notSwitch = true;

                            break;
                default:
                    notSwitch = true;
            }

        cool = new PrintWriter(new FileWriter(email+"investorsBusinessProduct.txt"));
        for (String s : investorProduct) {
            cool.println(s);
        }
        cool.close();
        if(!notSwitch) {
            Text nameLabel = new Text(label);
            ScrollPane sp = new ScrollPane();
            Scene scene = new Scene(sp, 300, 300);
            nameLabel.wrappingWidthProperty().bind(scene.widthProperty());
            sp.setFitToWidth(true);
            sp.setContent(nameLabel);
            windows = new Stage();
            windows.setScene(scene);
            windows.show();
        }
    }

    static void deplete() throws IOException {
        boolean noPerson = true;
        for (int m = 0; productsOnFile.size() > m; m++) {
            String[] consumedArray = productsOnFile.get(m).split("   ");
            if (prod.getText().toLowerCase().equals(consumedArray[0].toLowerCase())) {
                noPerson = false;
                timegod.write(prodAmount,prod.getText());
                double currentamount = Double.parseDouble(consumedArray[1]) - prodAmount;
                if (consumedArray.length == 2) {
                    productsOnFile.set(m, consumedArray[0] + "   " + currentamount);
                } else if (consumedArray.length == 3) {
                    productsOnFile.set(m, consumedArray[0] + "   " + currentamount + "   " + consumedArray[2]);
                }
            }
        }
        if (noPerson){
            label21.setText("This product does not exist.");
        }else{
            out = new PrintWriter(new FileWriter(email+"businessProduct.txt"));
            for (int I = 0; productsOnFile.size() - 1 > I; I++) {
                out.println(productsOnFile.get(I));
            }
            out.println(price2);
            out.close();
        }

    }
    static void sold() throws IOException {
        boolean noName = true;
        double number;
        timegod.write(subNumber,price, timegod.today());
        timegod.writeAll();
        for (int I = 0; I < productsOnFile.size(); I++) {
            StringArray = productsOnFile.get(I).split("   ");
            if (name1.getText().toUpperCase().equals(StringArray[0].toUpperCase())) {
                noName = false;
                number = Double.parseDouble(StringArray[1]);
                number = number - subNumber;
                if (number == 0) {
                    productsOnFile.remove(I);
                    break;
                }
                else if (number < 0){
                    label19.setText("You do not have enough product to sell that much!");
                    productQuaExists = false;
                    break;
                }
                number = Math.round(number * 10.0) / 10.0;
                StringArray[1] = Double.toString(number);
                if (StringArray.length == 2) {
                    productsOnFile.set(I, StringArray[0] + "   " + StringArray[1]);
                } else if (StringArray.length == 3) {
                    productsOnFile.set(I, StringArray[0] + "   " + StringArray[1] + "   " + StringArray[2].toUpperCase());
                }
            }
        }
        if(noName){
            label19.setText("This product does not exist");

        }
        if(productQuaExists && !noName) {
            if (price2 == 0) {
                price2 = price + Double.parseDouble(productsOnFile.get(productsOnFile.size() - 1));
            } else {
                price2 = price + price2;
            }
            productsOnFile.set(productsOnFile.size() - 1, Double.toString(price2));
        }
        out = new PrintWriter(new FileWriter(email +"businessProduct.txt"));
        for (int I = 0; productsOnFile.size() - 1 > I; I++) {
            out.println(productsOnFile.get(I));
        }
        out.println(price2);
        out.close();
    }
    static void Restock() throws IOException {

        if (productsOnFile.isEmpty()) {
            productsOnFile.add(productName.getText() + "   " + productAmount);
            productsOnFile.add("-" + prodPrice);
            out = new PrintWriter(new FileWriter(email + "businessProduct.txt"));
            for (String s : productsOnFile) {
                out.println(s);
            }
            out.close();
            timegod.write(prodPrice, timegod.today());
            timegod.writeAll();
        } else if (productsOnFile.size() == 1) {
            productsOnFile.add(0,productName.getText() + "   " + productAmount);
            productsOnFile.set(productsOnFile.size() - 1, Double.toString(Double.parseDouble(productsOnFile.get(productsOnFile.size() - 1)) - prodPrice));
            out = new PrintWriter(new FileWriter(email + "businessProduct.txt"));
            for (String s : productsOnFile) {
                out.println(s);
            }
            out.close();
            timegod.write(prodPrice, timegod.today());
            timegod.writeAll();
        } else {
            productsOnFile.set(productsOnFile.size() - 1, Double.toString(Double.parseDouble(productsOnFile.get(productsOnFile.size() - 1)) - prodPrice));
            for (int i = 0; productsOnFile.size() > i; i++) {
                reupProduct = productsOnFile.get(i).split("   ");
                if (reupProduct[0].toLowerCase().equals(productName.getText().toLowerCase())) {
                    timegod.write(prodPrice, timegod.today());
                    timegod.writeAll();
                    double newNumber;
                    reupProductExist = true;
                    newNumber = Double.parseDouble(reupProduct[1]) + productAmount;
                    reupProduct[1] = Double.toString(newNumber);
                    if (reupProduct.length == 2) {
                        productsOnFile.set(i, reupProduct[0] + "   " + reupProduct[1]);
                    } else {
                        productsOnFile.set(i, reupProduct[0] + "   " + reupProduct[1] + "   " + reupProduct[2]);
                    }

                }
            }
            if (!reupProductExist) {
                Product product = new Product(productName.getText().toUpperCase(), productAmount);
                out = new PrintWriter(new FileWriter(email + "businessProduct.txt"));
                for (int i = 0; i < productsOnFile.size() - 1; i++) {
                    out.println(productsOnFile.get(i));
                }
                out.println(product);
                if (productsOnFile.size() - 2 >= 0) {
                    out.println(productsOnFile.get(productsOnFile.size() - 1));
                    productsOnFile.add(productsOnFile.size() - 2, product.toString());
                }
            } else {
                out = new PrintWriter(new FileWriter(email + "businessProduct.txt"));
                for (int I = 0; productsOnFile.size() - 1 > I; I++) {
                    out.println(productsOnFile.get(I));
                }
                out.println(productsOnFile.get(productsOnFile.size() - 1));
            }
            out.close();
        }
    }

}
