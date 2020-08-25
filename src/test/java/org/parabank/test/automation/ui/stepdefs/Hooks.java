package org.parabank.test.automation.ui.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.parabank.test.automation.ui.driver.DriverFactory;
import org.parabank.test.automation.ui.driver.DriverManager;

public class Hooks {
    @Before
    public void setup() {
        // https://github.com/eliasnogueira/selenium-java-lean-test-achitecture/blob/24d71f724716a905364d08c45fc4f0256804c96f/src/main/java/test/BaseWeb.java
        DriverManager.setDriver(DriverFactory.init());
    }

    @After
    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();

        if (driver != null) {
            driver.close();
        }
    }
}
