package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://kazanexpress.ru";
        Configuration.browserSize = "1920x1080";
        //String browserSize = System.getProperty("size", "1920x1080");
        //String remoteBrowser = System.getProperty("remote", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

        //Configuration.browser= browserName;
        //Configuration.browserVersion= browserVersion;
        //Configuration.browserSize = browserSize;
        //if (remoteBrowser != null) {
        //    Configuration.remote = remoteBrowser;
        //}
    }

}
