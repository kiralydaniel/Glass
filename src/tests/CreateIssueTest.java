import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pageFactory.*;

import java.net.MalformedURLException;

public class CreateIssueTest extends BasePage {

    static LoginPage loginPage;
    static DashboardPage dashboard;

    static IssuePage issuePage;


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
    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue.csv")
    public void successfulCreateIssue(String project, String type, String summary) {
        dashboard.clickCreateBtn();
        dashboard.fillProjectField(project);
        dashboard.fillTypeField(type);
        dashboard.fillSummaryField(summary);
        dashboard.clickCreateIssueBtn();
        dashboard.clickCreatedIssueLink();
        String EXPECTED_TYPE = issuePage.getType();
        String EXPECTED_SUMMARY = issuePage.getSummary();
        Assertions.assertEquals(EXPECTED_TYPE, type);
        Assertions.assertEquals(EXPECTED_SUMMARY, summary);
        issuePage.deleteIssue();
        Assertions.assertTrue(issuePage.isDeleteIssueValidate());
    }

    @AfterEach
    public void tearDown(){
        loginPage.quit();
    }
}
