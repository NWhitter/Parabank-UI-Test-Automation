package org.parabank.test.automation.ui;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.parabank.test.automation.ui.webdriver.DriverFactory;
import org.parabank.test.automation.ui.webdriver.DriverManager;

public class Hooks {
    @Before
    public void setup() {
        // https://github.com/eliasnogueira/selenium-java-lean-test-achitecture/blob/24d71f724716a905364d08c45fc4f0256804c96f/src/main/java/test/BaseWeb.java
        DriverManager.setDriver(DriverFactory.init());
    }

    @After
    public void tearDown() {
        DriverManager.quit();
    }
}
