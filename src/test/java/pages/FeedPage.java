package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class FeedPage extends BasePage {
    int entriesAmount;
    int entriesAmountAfterAction;
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
                break;
        }
        driver.findElement(DELETE_ENTRY_BUTTON).click();
        return this;
    }

    public FeedPage ckickAllEntriesCheckbox() {
        if (checkAmountOfEntries().entriesAmount > 0) {
            driver.findElement(CHECKBOX_ALL_ENTRIES).click();
        }
        else {
            Assert.fail("There is no entries found");
        }
        return this;
    }

    public FeedPage checkAmountOfEntries() {
        List<WebElement> entries = driver.findElements(ENTRY);
        entriesAmount = entries.size();
        return this;
    }

    public FeedPage checkAmountOfEntriesAfterAddingEntry() {
        List<WebElement> entriesAfterAdding = driver.findElements(ENTRY);
        entriesAmountAfterAction = entriesAfterAdding.size();
        assertEquals(entriesAmountAfterAction, entriesAmount + 1, "Entry hasn't added");
        return this;
    }

    public FeedPage checkAmountOfEntriesAfterDeleteOneEntry() {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(checkboxSelected))));
        List<WebElement> entriesAfterAdding = driver.findElements(ENTRY);
        entriesAmountAfterAction = entriesAfterAdding.size();
        assertEquals(entriesAmountAfterAction, entriesAmount - 1, "Entry hasn't deleted");
        return this;
    }

    public FeedPage clickOneCheckbox() {
        if (checkAmountOfEntries().entriesAmount > 0) {
            driver.findElement(CHECKBOX).click();
        } else {
            Assert.fail("There is no entries for deleting");
        }
        return this;
    }

    public FeedPage checkAmountOfEntriesIsNull() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(descriptionVisible))));
        List<WebElement> entriesAfterAdding = driver.findElements(ENTRY);
        Assert.assertEquals(entriesAfterAdding.size(), 0, "Entries haven't deleted");
        return this;
    }

    public FeedPage findLastEntryAndCheckThatTagOnTheEntry(String textTag) {
        List<WebElement> allEntriesOnPage = driver.findElements(ENTRY);
        Assert.assertTrue(allEntriesOnPage.get(0).findElement(TAG_ON_THE_ENTRY).getText().contains(textTag), "There is no tags" + textTag + "on the Entry");
        return this;
    }

    public FeedPage clickAlert() {
        driver.switchTo().alert().accept();
        return this;
    }
}
