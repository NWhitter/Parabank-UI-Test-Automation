package org.parabank.test.automation.ui.utils;

import org.parabank.test.automation.ui.models.Customer;
import org.parabank.test.automation.ui.pages.*;

import java.time.LocalDate;


public class ContextSteps {
    public Customer customer;
    public String accountNumber;
    public String transferAccountNumber;
    public String newAccountNumber;
    public LocalDate transactionDate;
    public String accountType;

    public LandingPage landingPage;
    public AccountCreatedPage accountCreatedPage;
    public AccountsOverviewPage accountsOverviewPage;
    public SignUpPage signUpPage;
    public OpenNewAccountPage openNewAccountPage;
    public AccountDetailsPage accountDetailsPage;
}
