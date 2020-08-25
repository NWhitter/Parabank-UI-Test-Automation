package org.parabank.test.automation.ui.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigurationLoader {

    private ConfigurationLoader() {
    }

    public static Configuration getConfiguration() {
        return ConfigFactory.create(Configuration.class);
    }
}
