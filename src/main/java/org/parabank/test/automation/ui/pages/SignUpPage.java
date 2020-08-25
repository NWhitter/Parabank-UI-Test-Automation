package org.parabank.test.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.parabank.test.automation.ui.models.Customer;

public class SignUpPage extends AbstractPage {

    private final By firstNameInput = By.id("customer.firstName");
    private final By lastNameInput = By.id("customer.lastName");
    private final By streetInput = By.id("customer.address.street");
    private final By cityInput = By.id("customer.address.city");
    private final By stateInput = By.id("customer.address.state");
    private final By zipCodeInput = By.id("customer.address.zipCode");
    private final By phoneInput = By.id("customer.phoneNumber");
    private final By ssnInput = By.id("customer.ssn");
    private final By usernameInput = By.id("customer.username");
    private final By passwordInput = By.id("customer.password");
    private final By confirmInput = By.id("repeatedPassword");
    private final By submitInput = By.cssSelector("#customerForm > table > tbody > tr:nth-child(13) > td:nth-child(2) > input");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void goTo() {
        driver.get(baseUrl + "register.htm");
    }

    private SignUpPage typeFirstName(String firstName) {
        if (firstName != null) {
            driver.findElement(firstNameInput).sendKeys(firstName);
        }

        return this;
    }

    private SignUpPage typeLastName(String lastName) {
        if (lastName != null) {
            driver.findElement(lastNameInput).sendKeys(lastName);
        }

        return this;
    }

    private SignUpPage typeAddress(String street) {
        if (street != null) {
            driver.findElement(streetInput).sendKeys(street);
        }

        return this;
    }

    private SignUpPage typeCity(String city) {
        if (city != null) {
            driver.findElement(cityInput).sendKeys(city);
        }

        return this;
    }

    private SignUpPage typeState(String state) {
        if (state != null) {
            driver.findElement(stateInput).sendKeys(state);
        }

        return this;
    }

    private SignUpPage typeZipCode(String zipCode) {
        if (zipCode != null) {
            driver.findElement(zipCodeInput).sendKeys(zipCode);
        }

        return this;
    }

    private SignUpPage typePhone(String phone) {
        if (phone != null) {
            driver.findElement(phoneInput).sendKeys(phone);
        }

        return this;
    }

    private SignUpPage typeSsn(String ssn) {
        if (ssn != null) {
            driver.findElement(ssnInput).sendKeys(ssn);
        }

        return this;
    }

    private SignUpPage typeUsername(String username) {
        if (username != null) {
            driver.findElement(usernameInput).sendKeys(username);
        }

        return this;
    }

    private SignUpPage typePassword(String password) {
        if (password != null) {
            driver.findElement(passwordInput).sendKeys(password);
        }

        return this;
    }

    private SignUpPage typeConfirmPassword(String password) {
        if (password != null) {
            driver.findElement(confirmInput).sendKeys(password);
        }

        return this;
    }

    public AccountsOverviewPage clickSubmit() {
        driver.findElement(submitInput).click();

        return new AccountsOverviewPage(driver);
    }

    public AccountsOverviewPage submitForm(Customer credentials) {

        return this.typeFirstName(credentials.getFirstName())
                .typeLastName(credentials.getLastName())
                .typeAddress(credentials.getAddress())
                .typeCity(credentials.getCity())
                .typeState(credentials.getState())
                .typeZipCode(credentials.getZipCode())
                .typePhone(credentials.getPhone())
                .typeSsn(credentials.getSsn())
                .typeUsername(credentials.getUsername())
                .typePassword(credentials.getPassword())
                .typeConfirmPassword(credentials.getConfirm())
                .clickSubmit();

    }
}
