package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class EntriesPage extends BasePage {

    private static final By SECTION_TAGS = By.id("tags");

    public EntriesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public EntriesPage openPage() {
        driver.get("https://my.monkkee.com/#/entries");
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        Assert.assertTrue(driver.findElement(SECTION_TAGS).isDisplayed(),"Page hasn't loaded");
        return this;
    }
}
