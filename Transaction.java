package com.example.j.budjetto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Transaction {

    private int id;
    private String transType;
    private double amount;
    private String transDate;

    private static int lastTransactionIdUsed = 0;

    public Transaction(){
        this.id =++lastTransactionIdUsed;
        this.transType = "Expenditure";
        this.amount = 0.0;
        this.transDate = getDate();
    }

    public Transaction(String transType, double amount){
        this.id = ++lastTransactionIdUsed;
        this.transType = transType;
        this.amount = amount;
        this.transDate = getDate();
    }

    public Transaction(int id, String transType, double amount, String transDate){
        this.id = id;
        this.transType = transType;
        this.amount = amount;
        this.transDate = transDate;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTransType(String transType){
        this.transType = transType;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public void setTransDate(String transDate){
        this.transDate = transDate;
    }

    public int getId(){
        return id;
    }

    public String getTransType(){
        return transType;
    }

    public double getAmount(){
        return amount;
    }

    public String getTransDate(){
        return transDate;
    }

    private String getDate(){
        Date nyt = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(nyt);

    }

    @Override
    public String toString(){
        return  "Transaction type: " + this.transType +"\n" + "Transaction amount: " + this.amount + "\n" + "Transaction Time: " + this.transDate;
    }

}