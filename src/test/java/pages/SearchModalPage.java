package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class SearchModalPage extends BasePage {
    private static final By SEARCH_MODAL = By.id("search");
    private static final By SEARCH_INPUT = By.id("appendedInputButton");
    private static final By SEARCH_BUTTON = By.xpath("//*[@title='Search']");
    private static final By ENTRY = By.cssSelector(".entry-container");
    private static final By SEARCH_EXPLANATION = By.cssSelector(".search-explanation");

    public SearchModalPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage openPage() {
        driver.get("https://my.monkkee.com/#/entries");
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        driver.findElement(SEARCH_MODAL).isDisplayed();
        return this;
    }

    public SearchModalPage clickInput() {
        driver.findElement(SEARCH_INPUT).click();
        return this;
    }

    public SearchModalPage writeText(String textForSearch) {
        driver.findElement(SEARCH_INPUT).sendKeys(textForSearch);
        return this;
    }

    public SearchModalPage clickSearch() {
        driver.findElement(SEARCH_BUTTON).click();
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(SEARCH_EXPLANATION)));
        } catch (StaleElementReferenceException ex) {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(SEARCH_EXPLANATION)));
        }
        return this;
    }

    public SearchModalPage checkListOfEntriesContainsSearchText(String textForSearch) {
        int sizeOfEntriesWithNecessaryText = 0;
        List<WebElement> entriesAfterAdding = driver.findElements(ENTRY);
        for (int i = 0; i < entriesAfterAdding.size(); i++) {
            String entryText = entriesAfterAdding.get(i).getText().toLowerCase();
            boolean isEquals = entryText.contains(textForSearch.toLowerCase());
            if (isEquals) {
                sizeOfEntriesWithNecessaryText = sizeOfEntriesWithNecessaryText + 1;
            }
        }
        System.out.println(sizeOfEntriesWithNecessaryText);
        Assert.assertEquals(sizeOfEntriesWithNecessaryText, entriesAfterAdding.size(), "The number of records does not match the number of records with the required text");
        return this;
    }
}