package sample;

import static sample.TIMEGOD.theDate;

public class report {

    private double sales = 0;//today's sales
    private double profit = 0;//today's profit
    private double cost = 0;//today's cost
    private String date;

    report(String dates){
        this.sales = 0;
        this.profit = 0;
        this.cost = 0;
        this.date = dates;
    }//end of report contructor
    report(){
        this.sales = 0;
        this.profit = 0;
        this.cost = 0;
        this.date = theDate();
    }//end of report contructor


    //this method is to add onto what has been sold
    public void sell(double sales, double profit){
        this.sales += sales;
        this.profit += profit;
    }

    //this method is to add onto the cost so far
    public void bought(double cost){
        this.cost += cost;
    }//end of bought

    /**
     * these will be all the get and set methods down below
     *
     */
    public void setDate(String bob){
        this.date = bob;
    }//end of setDate

    public String getDate() {
        return date;
    }

    public double getSales() {
        return sales;
    }
    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getProfit() {
        return profit;
    }
    public void setProfit(double profit) {
        this.profit = profit;
    }


}
