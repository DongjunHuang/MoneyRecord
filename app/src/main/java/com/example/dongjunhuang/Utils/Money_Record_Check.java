package com.example.dongjunhuang.Utils;

/**
 * Created by dongjunhuang on 10/9/14.
 */


public class Money_Record_Check {

    /**Check money amount*/
    public static boolean Check_Money(String money_quota) {
        if(money_quota == null || money_quota.length() == 0)
            return false;
        char[] money = money_quota.toCharArray();
        for(Character q : money){
            if(q != '.' && (q < '0' && q > '9'))    return false;
        }
        boolean flagDot = false;
        boolean flagZero = false;
        for(int i = 0; i < money.length; i++){
            if(money[i] == '.' && !flagDot)
                return false;
            if(money[i] == '0' && !flagZero)
                return false;
            if(money[i] == '.')
                flagDot = !flagDot;
            if(money[i] != '.' && money[i] != '0'){
                flagDot = true;
                flagZero = true;
            }
        }
        return true;
    }
}
