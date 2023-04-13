import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import pageFactory.DashboardPage;
import pageFactory.GlassPage;
import pageFactory.LoginPage;
import pageFactory.Util;

import java.net.MalformedURLException;
import java.util.List;

public class GlassWorkflowTest {
    static GlassPage glassPage;
    static LoginPage loginPage;
    static DashboardPage dashboardPage;
     private static List<List> tabel;

    @BeforeAll
    public static void init() {
        dashboardPage = new DashboardPage();
        glassPage = new GlassPage();
        loginPage = new LoginPage();
        loginPage.navigateToDashboardLoginPage();
        loginPage.loggingIn(Util.VALID_USERNAME, Util.VALID_PASSWORD);
        dashboardPage.waitToPresentProfileBtn();
        loginPage.navigate("https://jira-expert.codecool.metastage.net/projects/BET?selectedItem=com.metainf.jira.plugin:glass-project-documentation#/home/issueTypes/10004/transitions");
        glassPage.clickSkipButton();
        glassPage.waitTable();
        tabel = glassPage.returnTable();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tableData.csv")
    public void tableValidation(int index, String FromStatus, String TransitionName, String ToStatus, String TransitionType, String TransitionScreen, String Conditions, String Validators, String PostFunctions){

        Assertions.assertEquals(FromStatus, tabel.get(index).get(0));
        Assertions.assertEquals(TransitionName, tabel.get(index).get(1));
        Assertions.assertEquals(ToStatus, tabel.get(index).get(2));
        Assertions.assertEquals(TransitionType, tabel.get(index).get(3));
        Assertions.assertEquals(TransitionScreen, tabel.get(index).get(4));
        Assertions.assertEquals(Conditions, tabel.get(index).get(5));
        Assertions.assertEquals(Validators, tabel.get(index).get(6));
        Assertions.assertEquals(PostFunctions, tabel.get(index).get(7));
    }

    @AfterAll
    public static void tearDown() {
        loginPage.quit();
    }
}

