package com.example.dongjunhuang.Utils;

import com.example.dongjunhuang.supportlib.Money_Record;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dongjunhuang on 11/26/14.
 */
public class ListSort {
    /**sort from highest amount to lowest amount*/
    public static List<Money_Record> sortFromHighToLow(List<Money_Record> objList){
        Collections.sort(objList, new Comparator<Money_Record>(){
           public int compare(Money_Record A, Money_Record B){
                return (int)(B.get_quota() - A.get_quota());
           }
        });
        return objList;
    }

    /**sort from highest amount to lowest amount*/
    public static List<Money_Record> sortFromLowToHigh(List<Money_Record> objList){
        Collections.sort(objList, new Comparator<Money_Record>(){
            public int compare(Money_Record A, Money_Record B){
                return (int)(A.get_quota() - B.get_quota());
            }
        });
        return objList;
    }

    /**sort from place*/
    public static List<Money_Record> sortFromPlace(List<Money_Record> objList){
        return null;
    }
}
