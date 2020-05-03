package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FeedPage extends BasePage {

    private static final By SECTION_TAGS = By.id("tags");

    public FeedPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FeedPage openPage() {
        driver.get("https://my.monkkee.com/#/entries");
        return this;
    }

    @Override
    public FeedPage isPageOpened() {
        Assert.assertTrue(driver.findElement(SECTION_TAGS).isDisplayed(), "Page hasn't loaded");
        return this;
    }
}
