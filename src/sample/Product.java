package sample;
import java.io.PrintWriter;

public class Product {
    String productName;
    double productAmount;
    String productInvestor;
    boolean hasAInvestor;
    String product;
    double investorAmount;
    char name;
    PrintWriter out;
    Product(String productName, double productAmount){
        this.productName = productName;
        this.productAmount = productAmount;
    }
    public String toString(){
        if(hasAInvestor){
            return productName + "   " + productAmount + "   " + productInvestor;
        }
        return productName + "   " + productAmount;
    }
    public void setInvestorAmount(char name, double investorAmount, String product) {
        this.name = name;
        this.investorAmount= investorAmount;
        this.product = product;
    }
    public String getInvestorAmount() {

        return name + " "+ investorAmount+ " " + product.toUpperCase();
    }
    public  void close(){
        out.close();
    }
}
