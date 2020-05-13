package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

@Log4j2
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
        log.info("Opening page with search modal");
        driver.get("https://my.monkkee.com/#/entries");
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        log.info("Page with search modal opened");
        driver.findElement(SEARCH_MODAL).isDisplayed();
        return this;
    }

    public SearchModalPage clickInput() {
        driver.findElement(SEARCH_INPUT).click();
        return this;
    }

    public SearchModalPage writeText(String textForSearch) {
        log.info("Writing text to search field " + textForSearch);
        driver.findElement(SEARCH_INPUT).sendKeys(textForSearch);
        return this;
    }

    public SearchModalPage clickSearch() {
        driver.findElement(SEARCH_BUTTON).click();
        try {
            log.info("Trying to click ");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(SEARCH_EXPLANATION)));
        } catch (StaleElementReferenceException ex) {
            log.warn("The element is removed from the DOM structure. Trying to click again ");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(SEARCH_EXPLANATION)));
        }
        return this;
    }

    public SearchModalPage checkListOfEntriesContainsSearchText(String textForSearch) {
        int sizeOfEntriesWithNecessaryText = 0;
        List<WebElement> entriesAfterAdding = driver.findElements(ENTRY);
        log.info("Checking that the page contains entries after searching");
        if (entriesAfterAdding.size() > 0) {
            log.info("Amount or entries is " + entriesAfterAdding.size());
            for (int i = 0; i < entriesAfterAdding.size(); i++) {
                String entryText = entriesAfterAdding.get(i).getText().toLowerCase();
                boolean isEquals = entryText.contains(textForSearch.toLowerCase());
                if (isEquals) {
                    log.info("Checking that entry contains searched text:" + textForSearch);
                    sizeOfEntriesWithNecessaryText = sizeOfEntriesWithNecessaryText + 1;
                }
            }
            log.info("Amount of entries contains searched text: " + sizeOfEntriesWithNecessaryText);
            log.info("Checking that amount of found entries (" + entriesAfterAdding.size() + ") the same tah amount of entries contain searched text (" + sizeOfEntriesWithNecessaryText + ")");
            Assert.assertEquals(sizeOfEntriesWithNecessaryText, entriesAfterAdding.size(), "The number of records does not match the number of records with the required text");
        } else {
            Assert.fail("No records found for this request");
            log.info("No records found for this request");
        }
        return this;
    }
}