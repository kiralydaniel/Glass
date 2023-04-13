import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pageFactory.BasePage;
import pageFactory.DashboardPage;
import pageFactory.LoginPage;
import pageFactory.Util;

import java.net.MalformedURLException;

public class CreateIssueTest extends BasePage {

    static LoginPage loginPage;
    static DashboardPage dashboard;


    @BeforeEach void init() {
        loginPage = new LoginPage();
        dashboard = new DashboardPage();
        loginPage.navigateToDashboardLoginPage();
        loginPage.loggingIn(Util.VALID_USERNAME, Util.VALID_PASSWORD);
        dashboard.waitToPresentProfileBtn();}

    @ParameterizedTest
    @CsvFileSource(resources = "/cancelIssue.csv")
    public void canselCreation(String project, String type, String summary){
        dashboard.clickCreateBtn();
        dashboard.fillProjectField(project);
        dashboard.fillTypeField(type);
        dashboard.fillSummaryField(summary);
        dashboard.clickCancelIssueBtn();
    }
    @AfterEach
    public void tearDown(){
        loginPage.quit();
    }
}
