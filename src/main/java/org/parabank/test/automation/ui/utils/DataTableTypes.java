package org.parabank.test.automation.ui.utils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import org.parabank.test.automation.ui.models.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTableTypes {
    @DataTableType(replaceWithEmptyString = "[blank]")
    public Customer customerEntry(DataTable entry) {
        Map<String, String> credentials = listsToMap(entry);
        return Customer.builder()
                .firstName(credentials.get("first name"))
                .lastName(credentials.get("last name"))
                .address(credentials.get("address"))
                .city(credentials.get("city"))
                .state(credentials.get("state"))
                .zipCode(credentials.get("zip code"))
                .phone(credentials.get("phone"))
                .ssn(credentials.get("ssn"))
                .username(credentials.get("username"))
                .password(credentials.get("password"))
                .confirm(credentials.get("confirm"))
                .build();
    }

    public Customer customerEntry(Map<String, String> entry) {
        return Customer.builder()
                .username(entry.get("username"))
                .password(entry.get("password"))
                .firstName(entry.get("first name"))
                .lastName(entry.get("last name"))
                .build();
    }

    private Map<String, String> listsToMap(DataTable entry) {
        Map<String, String> mapObject = new HashMap<>();
        for (List<String> row : entry.asLists()) {
            mapObject.put(row.get(0), row.get(1));
        }

        return mapObject;
    }
}
