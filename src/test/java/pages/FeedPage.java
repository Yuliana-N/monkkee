package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Log4j2
public class FeedPage extends BasePage {
    int entriesAmount;
    int entriesAmountAfterAction;
    int entriesAmountAfterAdding;
    private static final By SECTION_TAGS = By.id("tags");
    private static final By CREATE_ENTRY_BUTTON = By.id("create-entry");
    private static final By ENTRY = By.cssSelector(".entry-container");
    private static final By DELETE_ENTRY_BUTTON = By.id("delete-entries");
    private static final By CHECKBOX = By.xpath("//*[@ng-change = 'updateSelectionState()']");
    private static final By CHECKBOX_ALL_ENTRIES = By.xpath("//*[@ng-change = 'selectOrUnselectAll()']");
    private static final By TAG_ON_THE_ENTRY = By.xpath("//span[@class = 'tag ng-binding ng-scope']");
    String classDisabled = "//div[@class = 'btn-toolbar']//a[contains(@class, 'disabled')]";
    String checkboxSelected = "//div[(@class = 'checkbox-wrapper')]//input[@class = 'ng-valid ng-dirty']";
    String descriptionVisible = "//div[(@class = 'none centered')]";


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
        log.info("Main page opened");
        Assert.assertTrue(driver.findElement(SECTION_TAGS).isDisplayed(), "Page hasn't loaded");
        return this;
    }

    public EntryPage clickNewEntry() {
        driver.findElement(CREATE_ENTRY_BUTTON).click();
        EntryPage entries = new EntryPage(driver);
        entries.isPageOpened();
        return new EntryPage(driver);
    }

    public FeedPage clickDeleteButton() {
        while (true) {
            if (driver.findElements(By.xpath(classDisabled)).size() == 0)
                log.info("Check that the button has become clickable");
            break;
        }
        driver.findElement(DELETE_ENTRY_BUTTON).click();
        return this;
    }

    public FeedPage ckickAllEntriesCheckbox() {
        log.info("Checking that the page has entries");
        if (checkAmountOfEntries().entriesAmount > 0) {
            driver.findElement(CHECKBOX_ALL_ENTRIES).click();
        } else {
            Assert.fail("There is no entries found");
            log.error("There is no entries found");
        }
        return this;
    }

    public FeedPage checkAmountOfEntries() {
        log.info("Counting amount of entries before action");
        List<WebElement> entries = driver.findElements(ENTRY);
        entriesAmount = entries.size();
        log.info("Amount of entries " + entriesAmount);
        return this;
    }

    public FeedPage checkAmountOfEntriesAfterAddingEntry() {
        List<WebElement> entriesAfterAdding = driver.findElements(ENTRY);
        entriesAmountAfterAdding = entriesAfterAdding.size();
        log.info("Amount of entries after adding one entry: " + entriesAmountAfterAdding);
        assertEquals(entriesAmountAfterAdding, entriesAmount + 1, "Entry hasn't added");
        return this;
    }

    public FeedPage checkAmountOfEntriesAfterDeleteOneEntry() {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(checkboxSelected))));
        List<WebElement> entriesAfterAdding = driver.findElements(ENTRY);
        entriesAmountAfterAction = entriesAfterAdding.size();
        log.info("Amount of entries after deleting one entry: " + entriesAmountAfterAction);
        assertEquals(entriesAmountAfterAction, entriesAmount - 1, "Entry hasn't deleted");
        return this;
    }

    public FeedPage clickOneCheckbox() {
        if (checkAmountOfEntries().entriesAmount > 0) {
            driver.findElement(CHECKBOX).click();
        } else {
            Assert.fail("There is no entries for deleting");
            log.error("There is no entries for deleting");
        }
        return this;
    }

    public FeedPage checkAmountOfEntriesIsNull() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(descriptionVisible))));
        List<WebElement> entriesAfterAdding = driver.findElements(ENTRY);
        log.info("Amount of entries after deleting all entries:" + entriesAfterAdding);
        Assert.assertEquals(entriesAfterAdding.size(), 0, "Entries haven't deleted");
        return this;
    }

    public FeedPage findLastEntryAndCheckThatTagOnTheEntry(String textTag) {
        List<WebElement> allEntriesOnPage = driver.findElements(ENTRY);
        log.info("Searching and comparing the tag text for the entries with the name of selected tag: " + textTag);
        Assert.assertTrue(allEntriesOnPage.get(0).findElement(TAG_ON_THE_ENTRY).getText().contains(textTag), "There is no tags" + textTag + "on the Entry");
        return this;
    }

    public FeedPage clickAlert() {
        driver.switchTo().alert().accept();
        return this;
    }
}
