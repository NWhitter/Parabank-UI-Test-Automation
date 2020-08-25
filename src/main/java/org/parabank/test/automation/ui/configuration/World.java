package org.parabank.test.automation.ui.configuration;

import lombok.Data;
import org.parabank.test.automation.ui.models.Customer;
import org.parabank.test.automation.ui.pages.*;

import java.time.LocalDate;

@Data
public class World {
    private Customer customer;
    private String accountNumber;
    private String accountType;
    private String transferAccountNumber;
    private LocalDate transactionDate;

    private LandingPage landingPage;
    private AccountCreatedPage accountCreatedPage;
    private AccountsOverviewPage accountsOverviewPage;
    private SignUpPage signUpPage;
    private OpenNewAccountPage openNewAccountPage;
}
