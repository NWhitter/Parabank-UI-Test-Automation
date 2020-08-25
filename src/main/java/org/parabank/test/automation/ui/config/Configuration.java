package org.parabank.test.automation.ui.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Mutable;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:conf/application.properties"
})
public interface Configuration extends Mutable {

    @Key("base.url")
    String url();

    @Key("${base.url}/db.htm")
    String databasePageUrl();

    @Key("${base.url}/login.htm")
    String loginPageUrl();

    @Key("${base.url}/index.htm")
    String indexPageUrl();

    @Key("${base.url}/overview.htm")
    String accountOverviewPageUrl();

    @Key("${base.url}/register.htm")
    String accountCreatedPageUrl();

    @Key("browser")
    String browser();

    @Key("headless")
    Boolean headless();

    @Key("timeout")
    String timeout();
}
