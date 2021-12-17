package sample;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;
public class Employees{
    int count;
    private static Employees employees;
    Hashtable<String, Employee> track = new Hashtable<>();

    public static synchronized Employees getInstance(){
        if(employees == null){
            employees = new Employees();
        }
        return employees;
    }
    public void add(String a, Employee b){
        track.put(a, b);
        count++;
    }


    public int total(){
        return count;
    }

    public void delete(int a){
        track.remove(a);
        count--;
    }public void writeAll() throws IOException {
        FileWriter myWriter = new FileWriter("Employees.txt");
        track.forEach((k, v) -> {
            try {
                myWriter.write(v.getID() + " " + v.getName() + " " + v.getHoursWorked() + " " + v.getHourlyPay() + " " + v.getStatus() + " " + v.getPosition() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        myWriter.close();

    }
    public void readAll() throws FileNotFoundException {
        File myem = new File("Employees.txt");
        Scanner EMP = new Scanner(myem);
        while(EMP.hasNextLine()){
            String[] product = EMP.nextLine().split(" ");
            Employee employee = new Employee(Integer.parseInt(product[0]), product[1], Double.parseDouble(product[2]), Double.parseDouble(product[3]), product[4], product[5]);
            track.put(product[1],employee);
        }
    }public String printAllEmployees(){
        final String[] print = {""};
        track.forEach((k, v) -> print[0] = print[0] +  "\n" + v.getName());
return print[0];
    }



    public Employee getEmployee(String a){
        return track.get(a);
    }//end of method

}
