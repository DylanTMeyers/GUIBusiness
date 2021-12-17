package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class registers {
    LinkedList<Persons> People;
    private static registers registers;
    private registers(){
         People = new LinkedList<>();
    }
    public static synchronized registers getInstance(){
        if(registers == null){
            registers = new registers();
        }
        return registers;
    }
    public void addPeople(Persons persons){
        People.add(persons);
    }
    public void readAll() throws FileNotFoundException {
        File myObj = new File("people.txt");
        Scanner hey = new Scanner(myObj);
        while(hey.hasNextLine()){
            Persons persons = new Persons(hey.nextLine(), hey.nextLine(),hey.nextLine(),hey.nextLine(),hey.nextLine(),hey.nextLine());
            persons.setGender(hey.nextLine());
            addPeople(persons);
            hey.nextLine();
        }
        hey.close();

    }
    public boolean login(String email, String password){
        for(Persons persons: People){
            if(persons.getEmail().toLowerCase().equals(email.toLowerCase()) && persons.getPassword().equals(password)){
              return true;
            }
        }
        return false;
    }
    public Persons loggedIn(String email, String password){
        for(Persons persons: People){
            if(persons.getEmail().toLowerCase().equals(email.toLowerCase()) && persons.getPassword().equals(password)){
                return persons;
            }
        }
        return null;
    }
    public boolean alreadyReg(String email){
        for(Persons persons: People){
            if(persons.getEmail().toLowerCase().equals(email.toLowerCase())){
                return true;
            }
        }
        return false;

    }
    public void writeAll() throws IOException {
        FileWriter myWriter = new FileWriter("people.txt");
        for(Persons persons: People){

            myWriter.write(persons.getFirstName() +"\n");
            myWriter.write(persons.getLastName()+"\n");
            myWriter.write(persons.getEmail()+"\n");
            myWriter.write(persons.getMiddleInitial()+"\n");
            myWriter.write(persons.getPassword()+"\n");
            myWriter.write(persons.getGender()+"\n");
            myWriter.write(persons.getRace()+"\n");
            myWriter.write("------------------\n");

        }
        myWriter.close();
    }



}
