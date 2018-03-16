package com.acme.a3csci3130;

import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by saikishoreeppalagudem on 2018-03-14.
 */

public class MiscTasks {
    /*
    * This method performs a series of checks based on the requirements mentioned
    * and returns an ArrayList containing of errors, if any, or a success message
    *
    * @param    name               the name of the business as inputted by the user
    * @param    busines            sNum the 9-digit business number as inputted by the user
    * @param    primaryBusiness    the business type as selected by the user
    * @param    address            address inputted by the user
    * @param    province           province selected by the user
    * @return                      an ArrayList containing errors in user
    */
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

    /*
    * This method checks the length of the name parameter and ensures
    * that it is in between 2 and 48
    *
    * @param    name               the name of the business as inputted by the user
    * @return                      a boolean based on checks
    */
    public Boolean checkNameLen(String name){
        if((name.length() >= 2 ) && (name.length() <= 48 )) return  true;
        return false;
    }

    /*
    * This method ensures that the businessNumber parameter has
    * a value that has nine digits
    *
    * @param    businessNum        the Business Number as inputted by the user
    * @return                      a boolean based on checks
    */
    public Boolean checkbusNumLen(String businessNum){
        if(businessNum.matches("[0-9]+") && businessNum.length() == 9) return  true;
        return false;
    }

    /*
    * This method ensures that a value for the Primary Business is
    * selected
    *
    * @param    primaryBusiness    the Primary Business as inputted by the user
    * @return                      a boolean based on checks
    */
    public Boolean checkPrimBusNotEmpty(String primaryBusiness){
        if (!primaryBusiness.isEmpty()) return true;
        return false;
    }

    /*
    * This method ensures that the length of the address inputted by the user
    * is less than 50 characters in length
    *
    * @param    address            the address of the business as inputted by the user
    * @return                      a boolean based on checks
    */
    public Boolean checkAddrLen(String address){
        if (address.length() < 50) return true;
        return false;
    }

    /*
    * This method ensures that the Province field is not empty
    *
    * @param    province           the Province as inputted by the user
    * @return                      a boolean based on checks
    */
    public Boolean checkProvNotEmpty(String province){
        if (!province.isEmpty()) return true;
        return false;
    }
}
