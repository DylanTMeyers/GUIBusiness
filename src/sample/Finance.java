package sample;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Finance {
    String creditor;
    String email;
    boolean credyeah;
    ArrayList<String> transaction = new ArrayList<>(10);
    ArrayList<String> creditors = new ArrayList<>(10);
    File myOb;
    Scanner hey;
    Scanner naw;
    String[] credStringArray;
    double credNumber;
    Finance(String email) throws IOException {
        this.email = email;
        myOb = new File(email + "creditor.txt");
        naw = new Scanner(myOb);
        while(naw.hasNextLine()){
            creditors.add(naw.nextLine());
        }
    }
    public String getCreditors(){
        return this.creditor;
    }
    public void setCreditors(String creditor){
        this.creditor = creditor;
    }
    public void addCreditors(String name,int add) {
        credyeah = false;
        transaction.add(name + " gave $" + add + " in credit");
        for (int I = 0; I < creditors.size(); I++) {
            credStringArray = creditors.get(I).split(" ");
            if (name.toUpperCase().equals(credStringArray[0].toUpperCase())) {
                credyeah = true;
                credNumber = Double.parseDouble(credStringArray[1]);
                credNumber = credNumber + add;
                credNumber = Math.round(credNumber * 10.0) / 10.0;
                credStringArray[1] = Double.toString(credNumber);
                creditors.set(I, credStringArray[0] + " " + credStringArray[1]);
                break;
            }
        }
        if(!credyeah) {
            creditors.add(name + " " + add);
        }
    }
    public boolean subCreditors(String name,int sub){
        transaction.add(name + " paid back $" + sub );
        credyeah = false;
        for (int I = 0; I < creditors.size(); I++) {
            credStringArray = creditors.get(I).split(" ");
            if (name.toUpperCase().equals(credStringArray[0].toUpperCase())) {
                credyeah = true;
                credNumber = Double.parseDouble(credStringArray[1]);
                credNumber = credNumber - sub;
                credNumber = Math.round(credNumber * 10.0) / 10.0;
                if(credNumber<=0){
                    creditors.remove(I);
                    break;
                }
                credStringArray[1] = Double.toString(credNumber);
                creditors.set(I, credStringArray[0] + " " + credStringArray[1]);
                break;
            }
        }
        return credyeah;
    }
    public void fileSet() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(email+"creditor.txt"));
        for(String credit: creditors) {
            out.println(credit);
        }
        out.close();
    }
    public String printList(){
        String list = "";
        String[] credArray;
        if(creditors.size() == 0){
           return "\nNo creditors.";
        }
        for(String credit: creditors) {
            credArray  = credit.split(" ");
            list = list + "\n" + credArray[0] + " $" + credArray[1];
        }
        return list;
    }
}
