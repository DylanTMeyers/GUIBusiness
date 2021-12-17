package sample;

import java.io.FileWriter;
import java.io.IOException;

public class taxes {


    public static String lookup(String name,String email){

        if(Employees.getInstance().getEmployee(name) != null){
            print(name,email);
            return "\nWe found something";
        }
        else{
            return "\nThat employee could not be found!";
        }
    }

    public static void print(String name,String email){
        try{
            FileWriter myWriter = new FileWriter(email+"Taxes.txt");
            double hourlyPay = Employees.getInstance().track.get(name).getHourlyPay();
            int id = Employees.getInstance().track.get(name).getID();
            double totalpayy = Employees.getInstance().track.get(name).getTotalincome();
            double medicaretax = .029 * totalpayy;
            double statetax = .043 * totalpayy;
            double federaltax = 0;
            if(totalpayy > 0 && totalpayy < 9875){
                    federaltax = totalpayy * .010;
            }else if(totalpayy > 9875 && totalpayy < 40125){
                    federaltax = totalpayy * .012;
            }else if(totalpayy > 40125 && totalpayy < 85525){
                    federaltax = totalpayy * .022;
            }else if(totalpayy > 85525 && totalpayy < 163300){
                    federaltax = totalpayy * .024;
            }else if(totalpayy > 163300 && totalpayy < 207350){
                    federaltax = totalpayy * .032;
            }else if(totalpayy > 207350 && totalpayy < 518400){
                    federaltax = totalpayy * .035;
            }else if(totalpayy > 518400){
                    federaltax = totalpayy * .037;
            }

            myWriter.write("-----------------Taxes-----------------\n");
            myWriter.write("Employee ID: " + id + "      Employee Name: " + name + "\n");
            myWriter.write("Hourly pay: " + hourlyPay + "\n");
            myWriter.write("Yearly pay: " + totalpayy + "\n");
            myWriter.write("Medicare Tax: " + medicaretax + "\n");
            myWriter.write("State Tax: " + statetax+ "\n");
            myWriter.write("Federal Tax: " + federaltax+ "\n");
            myWriter.close();

        }catch(IOException e){
            System.out.println("ERROR!");
        }
    }
}
