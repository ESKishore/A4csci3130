package com.acme.a3csci3130;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;

/**
 * Created by saikishoreeppalagudem on 2018-03-15.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {
    /*
    * This espresso test tests CRUD operations performed through the app
    */

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(
            MainActivity.class);

    /*
    * Espresso test for the Create and Read operation
    */
    @Test
    public void checkCreateAndReadTasks(){
        SystemClock.sleep(1000);
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.etBusNum)).perform(typeText("342156789"));
        onView(withId(R.id.name)).perform(typeText("New Business Contact"));
        onView(withId(R.id.etAddress)).perform(typeText("Halifax"));
        closeSoftKeyboard();
        onView(withId(R.id.spinnerPrimaryBusiness)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.spinnerProvince)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView));
        SystemClock.sleep(1000);
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }

    /*
    * Espresso test for the Update operation
    */
    @Test
    public void checkUpdateTask(){
        SystemClock.sleep(1000);
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).perform(replaceText("RandomText"));
        closeSoftKeyboard();
        onView(withId(R.id.updateButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(withText("RandomText")));
    }


    /*
    * Espresso test for the Delete operation
    */
    @Test
    public void checkDeleteTask(){
        SystemClock.sleep(1000);
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(not(withText("RandomText"))));
    }


}
