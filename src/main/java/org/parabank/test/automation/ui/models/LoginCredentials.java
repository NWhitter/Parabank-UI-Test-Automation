package org.parabank.test.automation.ui.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@AllArgsConstructor
@Data
public class LoginCredentials {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginCredentials(Map<String, String> credientals) {
        this.username = credientals.get("username");
        this.password = credientals.get("password");
    }
}
