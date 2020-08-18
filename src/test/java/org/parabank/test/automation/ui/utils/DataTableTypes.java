package org.parabank.test.automation.ui.utils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import org.parabank.test.automation.ui.models.Customer;

import java.util.List;

public class DataTableTypes {
    @DataTableType
    public Customer customerEntry(DataTable entry) {
        List<List<String>> credentials = entry.asLists();
        return new Customer(credentials);
    }
}
