package com.example.dongjunhuang.Libs;

/**
 * Created by dongjunhuang on 10/9/14.
 */
//class to store the information of money record
public class Money_Record{
    private int money_id;
    private String money_source;
    private String money_type;
    private double money_quota;
    private String money_time;
    private String money_way;

    //constructor
    public Money_Record(){

    }
    public Money_Record(String money_source, String money_type, double money_quota, String money_way, String money_time){
        this.money_source = money_source;
        this.money_type = money_type;
        this.money_quota = money_quota;
        this.money_time = money_time;
        this.money_way = money_way;

    }

    //series of getter and setter
    public int get_id() {return money_id;}
    public void set_id(int id){money_id = id;}

    public String get_source(){return money_source;}
    public void set_source(String source){money_source = source;}

    public String get_type(){return money_type;}
    public void set_type(String type){ money_type = type;}

    public double get_quota(){return money_quota;}
    public void set_quota(double quota){money_quota = quota;}

    public String get_time(){return money_time;}
    public void set_time(String time){money_time = time;}

    public String get_way(){return money_way;}
    public void set_way(String way){money_way = way;}
}