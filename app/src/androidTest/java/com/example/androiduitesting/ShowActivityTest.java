package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void addCity() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(click());
        onView(withId(R.id.editText_name)).perform(androidx.test.espresso.action.ViewActions.typeText("Edmonton"), androidx.test.espresso.action.ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());
    }

    @Test
    public void testActivitySwitch() {
        onView(withText("Edmonton")).perform(click());
        onView(withId(R.id.city_name_text)).check(matches(isDisplayed()));
        onView(withId(R.id.back_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameConsistency() {
        onView(withText("Edmonton")).perform(click());
        onView(withId(R.id.city_name_text)).check(matches(withText("Edmonton")));
    }

    @Test
    public void testBackButton() {
        onView(withText("Edmonton")).perform(click());
        onView(withId(R.id.back_button)).perform(click());
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }
}