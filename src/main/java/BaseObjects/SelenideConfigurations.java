package BaseObjects;

import com.codeborne.selenide.Configuration;

import static Properties.PropertyReader.getProperties;

public class SelenideConfigurations {

    public SelenideConfigurations() {
    }

    protected void setSelenideConfigurations() {
        Configuration.baseUrl = getProperties().getProperty("baseUrl") == null ? System.getProperty("baseUrl") : getProperties().getProperty("baseUrl");
        Configuration.browser = getProperties().containsKey("browser") ? getProperties().getProperty("browser") : Configuration.browser;
        Configuration.headless = getProperties().containsKey("headless") ? Boolean.parseBoolean(getProperties().getProperty("headless")) : Configuration.headless;
    }

}
