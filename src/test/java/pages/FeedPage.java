package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.sql.SQLOutput;
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
        Assert.assertTrue(driver.findElement(SECTION_TAGS).isDisplayed(),"Page hasn't loaded");
        return this;
    }
    public EntryPage clickNewEntry(){
        driver.findElement(CREATE_ENTRY_BUTTON).click();
        EntryPage entries = new EntryPage(driver);
        entries.isPageOpened();
        return new EntryPage(driver);
    }
    public FeedPage clickDeleteButton(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //wait.until(ExpectedConditions.elementToBeClickable(DELETE_ENTRY_BUTTON));
        driver.findElement(DELETE_ENTRY_BUTTON).click();

        return this;
    }
    public FeedPage ckickAllEntriesCheckbox(){
        driver.findElement(CHECKBOX_ALL_ENTRIES).click();
        return this;
    }

    public FeedPage checkAmountOfEntries(){
        List<WebElement> entries = driver.findElements(ENTRY);
        entriesAmount = entries.size();
        System.out.println(entriesAmount);
        return this;
    }

    public FeedPage checkAmountOfEntriesAfterActionOnEntries(int value){
        List<WebElement> entriesAfterAdding = driver.findElements(ENTRY);
        entriesAmountAfterAction = entriesAfterAdding.size();
        assertEquals(entriesAmountAfterAction, entriesAmount + value, "Entry hasn't added");
        return this;
    }
    public FeedPage getListOfCheckBoxAndClickOneCheckbox(int entryNumber){
        List<WebElement> checkboxes = driver.findElements(CHECKBOX);
        checkboxes.get(entryNumber - 1).click();
        return this;
    }
    public FeedPage checkAmountOfEntriesIsNull(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> entriesAfterAdding = driver.findElements(ENTRY);
        Assert.assertEquals(entriesAfterAdding.size(),0, "Entries haven't deleted");
        return this;
    }
    public FeedPage clickAlert(){
        driver.switchTo().alert().accept();
        return this;
    }

}
