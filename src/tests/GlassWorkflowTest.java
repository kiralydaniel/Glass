import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import pageFactory.DashboardPage;
import pageFactory.GlassPage;
import pageFactory.LoginPage;
import pageFactory.Util;

import java.net.MalformedURLException;

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
        dashboardPage.waitToPresentProfileBtn();
        loginPage.navigate("https://jira-expert.codecool.metastage.net/projects/BET?selectedItem=com.metainf.jira.plugin:glass-project-documentation#/home/issueTypes/10004/transitions");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tableData.csv")
    public void tableValidation(int index, String FromStatus, String TransitionName, String ToStatus, String TransitionType, String TransitionScreen, String Conditions, String Validators, String PostFunctions){
        glassPage.clickSkipButton();
       System.out.println(index);
        System.out.println(glassPage.returnTable());
        Assertions.assertEquals(FromStatus, glassPage.returnTable().get(index).get(0));
        Assertions.assertEquals(TransitionName, glassPage.returnTable().get(index).get(1));
        Assertions.assertEquals(ToStatus, glassPage.returnTable().get(index).get(2));
        Assertions.assertEquals(TransitionType, glassPage.returnTable().get(index).get(3));
        Assertions.assertEquals(TransitionScreen, glassPage.returnTable().get(index).get(4));
        Assertions.assertEquals(Conditions, glassPage.returnTable().get(index).get(5));
        Assertions.assertEquals(Validators, glassPage.returnTable().get(index).get(6));
        Assertions.assertEquals(PostFunctions, glassPage.returnTable().get(index).get(7));
    }

    @AfterEach
    public void tearDown() {
        loginPage.quit();
    }
}

