package com.neura.runner;

import com.neura.base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {

    @Before
    public void setUp() {
        DriverFactory.initializeDriver();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
