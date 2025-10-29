package com.zaenal.stepdef;

import com.zaenal.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;



public class CucumberHooks extends BaseTest {

    @Before
    public void beforeTest() {
        getDriver();
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}
