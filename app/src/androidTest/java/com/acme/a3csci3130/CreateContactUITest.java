package com.acme.a3csci3130;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;

/**
 * Created by saikishoreeppalagudem on 2018-03-15.
 */

@RunWith(AndroidJUnit4.class)
public class CreateContactUITest {

    @Rule
    public ActivityTestRule<CreateContactAcitivity> createContactAcitivityActivityTestRule = new ActivityTestRule<>(
            CreateContactAcitivity.class);

    @Test
    public void checkBusNumET(){
        onView(withId(R.id.etBusNum)).perform(typeText("12345678"));
        closeSoftKeyboard();
        onView(withId(R.id.etBusNum)).check(matches(withText("12345678")));
    }

    @Test
    public void checkNameET(){
        onView(withId(R.id.name)).perform(typeText("RandomText"));
        closeSoftKeyboard();
        onView(withId(R.id.name)).check(matches(withText("RandomText")));
    }

    @Test
    public void checkAddressET(){
        onView(withId(R.id.etAddress)).perform(typeText("Address 1"));
        closeSoftKeyboard();
        onView(withId(R.id.etAddress)).check(matches(withText("Address 1")));
    }

    @Test
    public void checkSpinnerPrimaryBus(){
        onView(withId(R.id.spinnerPrimaryBusiness)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.spinnerPrimaryBusiness)).check(matches(withSpinnerText("Fisher")));
    }

    @Test
    public void checkSpinnerProvince(){
        onView(withId(R.id.spinnerProvince)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.spinnerProvince)).check(matches((withSpinnerText("AB"))));
    }

    @Test
    public void checkButtonClick(){
        onView(withId(R.id.submitButton));
    }
}
