package org.parabank.test.automation.ui.configuration;

import org.aeonbits.owner.ConfigFactory;

public class ConfigurationLoader {

    private ConfigurationLoader() {
    }

    public static Configuration getConfiguration() {
        return ConfigFactory.create(Configuration.class);
    }
}