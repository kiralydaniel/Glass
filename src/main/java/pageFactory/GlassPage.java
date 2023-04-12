package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;

public class GlassPage extends BasePage{
    @FindBy(css = "#app-root > div > div > div:nth-child(3) > div:nth-child(4) > div > div > div.transitions-table-holder > div > table > tbody")
    WebElement table;

    public WebElement returnTable(){
        return table;
    }
}
