package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;


public class WebDriverFactory {
    private static WebDriver webDriver = null;

    public static WebDriver createWebDriver(String browserName) {
        if (webDriver == null) {
                switch (browserName) {
                    case "Firefox": {
                        webDriver = new FirefoxDriver();
                        break;
                    }
                    case "Chrome": {
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--remote-allow-origins=*");
                        webDriver = new ChromeDriver(chromeOptions);
                        break;
                    }
                }
            }
        return webDriver;
    }

    public static void shutDown() {
        webDriver.quit();
        webDriver = null;
    }
}
