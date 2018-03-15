package com.acme.a3csci3130;

import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by saikishoreeppalagudem on 2018-03-14.
 */

public class MiscTasks {
    public ArrayList<String> performChecks(String name, String businessNum, String primaryBusiness, String address, String province){
        ArrayList<String> returnMsg = new ArrayList<>();
        if (checkNameLen(name) == false)  returnMsg.add("Name should be greater than 2 and less than 48 characters");
        if (checkbusNumLen(businessNum) == false) returnMsg.add("Business Number should be 9 digits");
        if (checkPrimBusNotEmpty(primaryBusiness) == false) returnMsg.add("Select value for Primary Business");
        if (checkAddrLen(address) == false) returnMsg.add("Address cannot be more than 50 characters");
        if (checkProvNotEmpty(province) == false) returnMsg.add("Select value for Province");
        if (returnMsg.isEmpty()) returnMsg.add("All checks passed!");
        return returnMsg;

    }

    public Boolean checkNameLen(String name){
        if((name.length() >= 2 ) && (name.length() <= 48 )) return  true;
        return false;
    }

    public Boolean checkbusNumLen(String businessNum){
        if(businessNum.matches("[0-9]+") && businessNum.length() == 9) return  true;
        return false;
    }

    public Boolean checkPrimBusNotEmpty(String primaryBusiness){
        if (!primaryBusiness.isEmpty()) return true;
        return false;
    }

    public Boolean checkAddrLen(String address){
        if (address.length() < 50) return true;
        return false;
    }

    public Boolean checkProvNotEmpty(String province){
        if (!province.isEmpty()) return true;
        return false;
    }
}
