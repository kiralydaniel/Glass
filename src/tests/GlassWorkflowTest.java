import com.codeborne.selenide.impl.WebElementSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageFactory.DashboardPage;
import pageFactory.GlassPage;
import pageFactory.LoginPage;
import pageFactory.Util;

import java.net.MalformedURLException;
import java.sql.Driver;

public class GlassWorkflowTest {
    static GlassPage glassPage;
    static LoginPage loginPage;
    static DashboardPage dashboardPage;

    @BeforeEach
    public void init() throws MalformedURLException, InterruptedException {
        dashboardPage = new DashboardPage();
        glassPage = new GlassPage();
        loginPage = new LoginPage();
        loginPage.navigateToDashboardLoginPage();
        loginPage.loggingIn(Util.VALID_USERNAME, Util.VALID_PASSWORD);
        Thread.sleep(2000);
        loginPage.navigate("https://jira-expert.codecool.metastage.net/projects/BET?selectedItem=com.metainf.jira.plugin:glass-project-documentation#/home/issueTypes/10004/transitions");
    }

    @Test
    public void printTable(){
        glassPage.clickSkipButton();
        System.out.println(glassPage.returnTable());
        System.out.println(glassPage.returnTable().size());
    }


}
