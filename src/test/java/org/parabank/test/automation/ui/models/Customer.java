package org.parabank.test.automation.ui.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class Customer {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String ssn;
    private String username;
    private String password;
    private String confirm;

    public Customer(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(List<List<String>> credentials) {
        for (List<String> credential : credentials) {
            switch (credential.get(0).toLowerCase()) {
                case "first name":
                    firstName = credential.get(1);
                    break;
                case "last name":
                    lastName = credential.get(1);
                    break;
                case "address":
                    address = credential.get(1);
                    break;
                case "city":
                    city = credential.get(1);
                    break;
                case "state":
                    state = credential.get(1);
                    break;
                case "zip code":
                    zipCode = credential.get(1);
                    break;
                case "phone":
                    phone = credential.get(1);
                    break;
                case "ssn":
                    ssn = credential.get(1);
                    break;
                case "username":
                    username = credential.get(1);
                    break;
                case "password":
                    password = credential.get(1);
                    break;
                case "confirm":
                    confirm = credential.get(1);
                    break;
            }
        }
    }
}
