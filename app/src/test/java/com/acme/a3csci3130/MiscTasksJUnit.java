package com.acme.a3csci3130;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

/**
 * Created by saikishoreeppalagudem on 2018-03-14.
 */
@RunWith(JUnit4.class)
public class MiscTasksJUnit extends TestCase{

    /*
    * This Junit tests the different checks performed in the MiscTasks java
    * class. Each test is written for methods defined in the java class
     */
    private String testName0 = "A";
    private String testName1 = "Name";
    private String testBusNo0 = "1234a56789";
    private String testBusNo1 = "123456789";
    private String testPriBus0 = "";
    private String testPriBus1 = "Fisher";
    private String testProvince0 = "";
    private String testProvince1 = "AB";
    private String testAddress0 = "This line of text is more than 50 characters in length";
    private String testAddress1 = "Waterfront, Halifax";
    private ArrayList<String> testOutput0 = new ArrayList<>();
    private ArrayList<String> testOutput1 = new ArrayList<>();


    @Before
    public void initialiseArrays(){
        testOutput0.add("Name should be greater than 2 and less than 48 characters");
        testOutput0.add("Business Number should be 9 digits");
        testOutput0.add("Select value for Primary Business");
        testOutput0.add("Address cannot be more than 50 characters");
        testOutput0.add("Select value for Province");
        testOutput1.add("All checks passed!");
    }

    /*
    * This method performs a test on the series of checks defined in the MiscTasks
    * java class.
    *
    * @param    name               the name of the business as inputted by the user
    * @param    busines            sNum the 9-digit business number as inputted by the user
    * @param    primaryBusiness    the business type as selected by the user
    * @param    address            address inputted by the user
    * @param    province           province selected by the user
    * @return                      an ArrayList containing errors in user
    */

    private Boolean doTest(String name, String busNo, String primaryBus, String province, String address, ArrayList<String> expectedOutput){

        ArrayList<String> generatedOutput = new ArrayList<>();
        MiscTasks miscTasks = new MiscTasks();
        generatedOutput = miscTasks.performChecks(name, busNo, primaryBus, address, province);
        System.out.println("expectedOutput: " + expectedOutput);
        System.out.println("generatedOutput: " + generatedOutput);
        return expectedOutput.equals(generatedOutput);
    }

    /*
    * Test to see if all checks fail
    * */
    @Test
    public void allChecksFail(){
        assertTrue(doTest(testName0, testBusNo0, testPriBus0, testProvince0, testAddress0, testOutput0));
    }

    /*
    * Test to see if all checks pass
    * */
    @Test
    public void allChecksPass(){
        assertTrue(doTest(testName1, testBusNo1, testPriBus1, testProvince1, testAddress1, testOutput1));
    }
}
