package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import java.net.MalformedURLException;

public class GlassPage extends BasePage{

    @FindBy(xpath = "//*[@id=\"app-root\"]/div/div/div[3]/div[4]/div/div/div[2]/div/table")
    WebElement table;

    @FindBy(css = "#jira > div.atlaskit-portal-container > div:nth-child(2) > div > div:nth-child(3) > div > div > div > div > div > div:nth-child(2) > button > span")
    WebElement skipButton;

//    WebElement table2 = driver.findElement(By.cssSelector("#app-root > div > div > div:nth-child(3) > div:nth-child(4) > div > div > div.transitions-table-holder > div > table"));

    public List<List> returnTable(){
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<List> table = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<String> rowList = new ArrayList<>();
            for (WebElement cell : cells) {
                String text = cell.getText();

                rowList.add(text);
            }
            table.add(rowList);
        }
        table.remove(0);
        return table;
    }

    public void clickSkipButton(){
        wait.until(ExpectedConditions.elementToBeClickable(skipButton));
        skipButton.click();
    }
}
