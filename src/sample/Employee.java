package sample;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.*;
import java.io.*;

public class Employee{

	private int ID;
	private String name;
	private double hoursWorked;
	private double hourlyPay;
	private String status;
	private String position;
	private double totalincome;
	private double monthlyIncome; // not used yet
	private FileWriter myWriter;


	public Employee(int ID, String name, double hoursWorked, double hourlyPay, String status, String position ){
		this.ID = ID;
		this.name = name;
		this.hoursWorked = hoursWorked; 
		this.hourlyPay = hourlyPay;
		this.status = status; 
		this.position = position;
		this.totalincome = hourlyPay * hoursWorked;
	}
	public String getName(){
		return name;
	}

	public int getID(){
		return ID;
	}
	public double getPay(){
		return hoursWorked * hourlyPay;
	}
	public double getHoursWorked(){
		return hoursWorked;
	}
	public void setHoursWorked(int hours){
		this.hoursWorked = hours;
	}
	public double getHourlyPay(){
		return hourlyPay;
	}
	public void setHourlyPay(int hours){
		this.hoursWorked = hours;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getPosition(){
		return position;
	}
	public void setPosition(String position){
		this.position = position;
	}
	public String toString(){
		return "ID: " + ID + "\nName: " + name + "\nHours Worked: " + hoursWorked + "\nHourly Pay: " + hourlyPay + "\nStatus: " + status + " \nPosition: " + position;
	}
	public void printToFile(){
		// put data in a text file

	}
	public double getTotalincome(){
		return totalincome;
	}
	public void setTotalincome(double a){
		this.totalincome = a;
	}
	public void addHoursWorked(int hours){
		hoursWorked = hours + hoursWorked;
	}
}
