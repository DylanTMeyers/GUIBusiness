package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TIMEGOD {


    /**
     * these variables down below needs to be kept track of
     * inside of a text file when it's saved
     * there is a method for it
     */
    private final ArrayList<String> TimeIsAll = new ArrayList<>(10);
    private report[] year;//an array to store data when it's not leap year
    private report[] leapYear;//an array to store data when it's a leap year
    private boolean normalYear;//boolean to say if leap year or not
    private int theDay; // the number of the assumed today day
    private static String today;
    private String email;

    TIMEGOD(String email) {
        this.email = email;
        this.year = new report[365];
        this.leapYear = new report[366];
        this.theDay = 0;
        for(int i = 0; i < 365 ; i++) {
            String dates;

            //to get calendar instance
            Calendar cal = Calendar.getInstance();

            //subtract 1 from calendar current date
            cal.add(Calendar.DATE, -i);

            //format date
            DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

            //get formatted date
            dates = dateFormat.format(cal.getTime());
            if(i == 0){
                today = dates;
            }

            if(dates.startsWith("31 Dec")){
                break;
            }

            report joe = new report(dates);
            year[today() - i] = joe;
        }



    }//end of timegod constructor

    /**
     * this grabs the current date by the form
     * mm/dd/yyyy
     * @return
     */
    public static String theDate(){
        return today;
    }//end of date

    /**
     * this entire method is to get today's date to keep track of things
     * it grabs all the info on today and returns a number that's the
     * current day in the year from the array to use for the day
     * I find an easier way to do this later, by my god isn't this code
     * BEUTIFUL    update: i deleted it....
     * @return
     */
    //method to get current date
    public int today() {

        Date today = new Date();//gets the date
        //formats it to mm dd yyyy
        SimpleDateFormat today2 = new SimpleDateFormat("D");
        String b = today2.format(today);//turns into string

        return Integer.parseInt(b);//returns as an intiger
    }//end of today method


    /**
     * this method returns wether it's a new day or not
     * it does this by keeping track of the assumed today
     * and a recount of today's date. If it's the same, it
     * is not a new day, if it's different it's a new day
     * this method is being used in the method below, don't worry
     * about this
     * @return
     */
    //this method will be used to see if it's a new day
    public boolean newDay(){

        int bob = today();//grabs the number of today's date
        if(bob==theDay){//it today matches with theDay it's the same day
            return false;
        }
        else{//if it doesn't match then it's a new day
            theDay = bob;//makes this the new day
            return true;
        }

    }//end of newDay

    /**
     * checks to see if this an empty day or not
     * @param bob
     * @return
     */
    //method to see if there is no report in that day
    //don't worry about this method
    private boolean empty(int bob){
        if(this.year[bob] == null){//checks this day if it's null
            return true;//it is empty
        }//end of if
        else{//if it's not empty there is a report
            return false;
        }//end of else
    }//end of empty




     //Just takes all lines from file
    public void readAll() throws FileNotFoundException {
        File time = new File(email+"TimeGod.txt");
        Scanner Time = new Scanner(time);
        while(Time.hasNextLine()) {

            String[] product = Time.nextLine().split(":");

            for (int i = 1; i <=today();) {


                if(year[i].getDate().equals(product[0])) {
                    String[] products = product[1].split(" ");
                    if (products[0].equals("Sold")) {
                        this.write(Double.parseDouble(products[1]), Double.parseDouble(products[5]), i);
                    } else if (products[0].equals("Bought")) {
                        this.write(Double.parseDouble(products[2]), i);
                    }
                    if(Time.hasNextLine()){
                        product = Time.nextLine().split(":");
                        i =1;
                    }
                    else{
                        break;
                    }
                }
                else{
                    i++;
                }
            }
        }
    }


    //Just takes all lines from Array List and writes it to file.
    public void writeAll() throws IOException {
        FileWriter myWriter = new FileWriter(email+"TimeGod.txt");
            for(String timegod:TimeIsAll)
            try {
                myWriter.write(timegod+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        myWriter.close();
    }
    /**
     * call this method first to make a report for the day
     * will be used to add on every sale to keep track
     * if it's not a newday, no new report will be made
     * and then the record will be kept track of
     * this may happen in case someone re opens this program
     */
    //method to put in today's sales, profit, cost
    public void write(double profit, double sales, int bob){
        TimeIsAll.add( this.year[bob].getDate() +":Sold " + sales + " units for $ " + profit); // Loads into Array List for saving later

            this.year[bob].sell(profit,sales);//keep track of sale/profit on day

    }//end of write

    /**
     * this method will be used when you buy something
     * it kepps track of the total cost of how much you spend
     * that day to buy a product, use this when you purchase
     * a new product like during a reup
     */
    public void write(double cost, int bob){

        TimeIsAll.add( this.year[bob].getDate()+":Bought $ " +  cost +" worth of product");// Loads into Array List for saving later


            this.year[bob].bought(cost);//increases cost in this day

    }//end of buys
    public void write(double prod, String name){

        TimeIsAll.add( this.year[today()].getDate()+":" + prod + " units of " + name + " depleted");// Loads into Array List for saving later


    }//end of buys
    public String printAll(){
        String print = "";
        for(String time: TimeIsAll){
            print = print + "\n" + time;
        }
        return print;
    }

    /**
     * this method is to show today's report
     * it shows sales profits and cost of that day
     */
    //method to show today's stats
    public String todayReport(){
        String today = "";
        int bob = today();//gets the date

        //gets all the cost sales and profit for the day
        double cost = this.year[bob].getCost();
        double sales = this.year[bob].getSales();
        double profit = this.year[bob].getProfit();
        String date = this.year[bob].getDate();

        today = "\n " + today;
        today = today + "\n Date: " +  date;
        today = today + "\n Sales UwU: " + sales;
        today= today + "\n Profits UwU: $" + profit;
        today = today+ "\n Cost UwU: $" +cost;
        today = today +" \n ";
        return today;
    }//end of todayReport

    /**
     * this is a method that grabs the day report of whatever day
     * it is being told to do, if there is not report it says that
     * nothing happend that day
     * @param bob
     */
    //method to show a day's report
   public String thatDayReport(int bob){
       String today = "";
        if(!empty(bob)) {
            double cost = this.year[bob].getCost();
            double sales = this.year[bob].getSales();
            double profit = this.year[bob].getProfit();
            String date = this.year[bob].getDate();

            today = "\n " + today;
            today = today + "\n Date: " +  date;
            today = today + "\n Sales UwU: " + sales;
            today= today + "\n Profits UwU: $" + profit;
            today = today+ "\n Cost UwU: $" +cost;
            today = today +" \n ";
            return today;
        }
        return null;
    }//end of thatDayReport
    public report thatDayReports(int bob){
        if(!empty(bob)) {
            return this.year[bob];
        }
        return null;
    }//end of thatDayReport
    /**
     * this method gets the year report, to do this, it simply
     * prints out the entire year
     */
    //method to show year's stats
    public String yearReport(){
        String year = "";
        for(int i = 0 ; i < 365; i++){
            try {
                year = year + "\n\n Date: " + this.year[i].getDate();
                year = year + "\n Sales: " + this.year[i].getSales();
                year = year + "\n Profit: $" + this.year[i].getProfit();
                year = year + "\n Cost: $" + this.year[i].getCost();
                year = year + "\n ";
            }
            catch(NullPointerException e){

            }
        }//end of for
        return year;
    }//end of yearReport


    /**
     * this gets the month reports by grabbing the day of the month
     * and then looping up to it and printing everything
     */
    //method to show months stats
    public String monthReport(){
        String month = "";
        Date date = new Date();//grabs the date
        SimpleDateFormat day = new SimpleDateFormat("d");
        String bob = day.format(date);//gets the day in the month
        int tom = Integer.parseInt(bob);//puts it into a number
        int today = today();//todays number in year

        for(int i = 1; i <=tom; i++){
          month = month +  thatDayReport((today-tom)+i);
        }//end of for
        return month;
    }//end of monthReport

    /**
     * this method prints out the current week's records
     * this is done by seeing what day it is and gathers the data
     * of it, if there is no data, it shows happy faces
     */
    //method to show week's stats
    public String weekReport(){
        String week = "";
        Date date = new Date();//grabs the date
        //formats it to see what day of the week it is
        SimpleDateFormat day = new SimpleDateFormat("E");
        String bob = day.format(date);//gets the day
        int tom = today();//the day of the current year

        //to see what day it is
        switch(bob){
            case "Mon":
                week = week + thatDayReport(tom-1);
                week = week+ thatDayReport(tom);
                break;
            case "Tue":
                week = week+ thatDayReport(tom-2);
                week = week+ thatDayReport(tom-1);
                week = week+ (tom);
                break;
            case "Wed":
                week = week+ thatDayReport(tom-3);
                week = week+  thatDayReport(tom-2);
                week = week+  thatDayReport(tom-1);
                week = week+  thatDayReport(tom);
                break;
            case "Thu":
                week = week+  thatDayReport(tom-4);
                week = week+  thatDayReport(tom-3);
                week = week+  thatDayReport(tom-2);
                week = week+  thatDayReport(tom-1);
                week = week+  thatDayReport(tom);
                break;
            case "Fri":
                week = week+  thatDayReport(tom-5);
                week = week+  thatDayReport(tom-4);
                week = week+  thatDayReport(tom-3);
                week = week+  thatDayReport(tom-2);
                week = week+  thatDayReport(tom-1);
                week = week+  thatDayReport(tom);
                break;
            case "Sat":
                week = week+    thatDayReport(tom-6);
                week = week+   thatDayReport(tom-5);
                week = week+  thatDayReport(tom-4);
                week = week+  thatDayReport(tom-4);
                week = week+  thatDayReport(tom-3);
                week = week+  thatDayReport(tom-2);
                week = week+ thatDayReport(tom-1);
                week = week+ thatDayReport(tom);
                break;
            case "Sun":
                week = week+  thatDayReport(tom);
                break;
        }//end of switch

return week;
    }//end of weekReport

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    public double getThisMonth(){
        double monthProf = 0;
        Date date = new Date();//grabs the date
        SimpleDateFormat day = new SimpleDateFormat("d");
        String bob = day.format(date);//gets the day in the month
        int tom = Integer.parseInt(bob);//puts it into a number
        int today = today();//todays number in year

        for(int i = 1; i <=tom; i++){
            monthProf = monthProf + year[(today-tom)+i].getProfit();
        }//end of for
        return monthProf;

    }//end of monthReport
    public double getLastMonth(){
        double monthProf = 0;
        Date date = new Date();//grabs the date
        SimpleDateFormat day = new SimpleDateFormat("d");
        String bob = day.format(date);//gets the day in the month
        int tom = Integer.parseInt(bob);//puts it into a number
        int today = today();//todays number in year

        for(int i = 1; i <=Integer.parseInt(year[today-tom].getDate().substring(0,2)); i++){
            monthProf = monthProf + year[(today-tom)-i].getProfit();
        }//end of for
    return monthProf;
    }//end of monthReport
    public double getLastWeek(){
        double monthProf = 0;
        Date date = new Date();//grabs the date
        //formats it to see what day of the week it is
        SimpleDateFormat day = new SimpleDateFormat("E");
        String bob = day.format(date);//gets the day
        //to see what day it is
        switch(bob){
            case "Mon":
                for(int i = 0; i<7;i++){
                    monthProf = monthProf+ year[(today()-2)-i].getProfit();
                }
                break;
            case "Tue":
                for(int i = 0; i<7;i++){
                    monthProf = monthProf+ year[(today()-3)-i].getProfit();
                }
                break;
            case "Wed":
                for(int i = 0; i<7;i++){
                    monthProf = monthProf+ year[(today()-4)-i].getProfit();
                }
                break;
            case "Thu":
                for(int i = 0; i<7;i++){
                    monthProf = monthProf+ year[(today()-5)-i].getProfit();
                }
                break;
            case "Fri":
                for(int i = 0; i<7;i++){
                    monthProf = monthProf+ year[(today()-6)-i].getProfit();
                }
                break;
            case "Sat":
                for(int i = 0; i<7;i++){
                    monthProf = monthProf+ year[(today()-7)-i].getProfit();
                }
                break;
            case "Sun":
                for(int i = 0; i<7;i++){
                    monthProf = monthProf+ year[(today()-1)-i].getProfit();
                }
                break;
        }//end of switch
        return monthProf;
    }//end of monthReport
        public double getThisWeek(){
            double monthProf = 0;
            Date date = new Date();//grabs the date
            //formats it to see what day of the week it is
            SimpleDateFormat day = new SimpleDateFormat("E");
            String bob = day.format(date);//gets the day


            int tom = today();//the day of the current year

            //to see what day it is
            switch(bob){
                case "Mon":
                    monthProf = monthProf + year[tom-1].getProfit();
                    monthProf= monthProf +year[tom].getProfit();
                    break;
                case "Tue":
                    monthProf = monthProf + year[tom-2].getProfit();
                    monthProf = monthProf +year[tom-1].getProfit();
                    monthProf = monthProf + year[tom].getProfit();
                    break;
                case "Wed":
                    monthProf = monthProf +year[tom-3].getProfit();
                    monthProf = monthProf + year[tom-2].getProfit();
                    monthProf = monthProf + year[tom-1].getProfit();
                    monthProf = monthProf +year[tom].getProfit();
                    break;
                case "Thu":
                    monthProf = monthProf + year[tom-4].getProfit();
                    monthProf = monthProf + year[tom-3].getProfit();
                    monthProf = monthProf + year[tom-2].getProfit();
                    monthProf = monthProf + year[tom-1].getProfit();
                    monthProf = monthProf + year[tom].getProfit();
                    break;
                case "Fri":
                    monthProf = monthProf +year[tom-5].getProfit();
                    monthProf = monthProf + year[tom-4].getProfit();
                    monthProf = monthProf + year[tom-3].getProfit();
                    monthProf = monthProf + year[tom-2].getProfit();
                    monthProf = monthProf + year[tom-1].getProfit();
                    monthProf = monthProf + year[tom].getProfit();
                    break;
                case "Sat":
                    monthProf = monthProf + year[tom-6].getProfit();
                    monthProf = monthProf + year[tom-5].getProfit();
                    monthProf = monthProf + year[tom-4].getProfit();
                    monthProf = monthProf + year[tom-3].getProfit();
                    monthProf = monthProf + year[tom-2].getProfit();
                    monthProf = monthProf + year[tom-1].getProfit();
                    monthProf = monthProf + year[tom].getProfit();
                    break;
                case "Sun":
                    monthProf = monthProf + year[tom].getProfit();
                    break;
            }//end of switch
            return monthProf;
    }//end of monthReport
}//end of TIMEGOD
