package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class EntryPage extends BasePage {

    private static final By CKE_PANEL_ID = By.id("cke_editable");
    private static final By TEXTBOX = By.id("editable");
    private static final By HOME_BUTTON = By.id("back-to-overview");
    private static final By BOLD_BUTTON = By.cssSelector(".cke_button__bold");
    private static final By NEW_TAG_INPUT = By.id("new-tag");
    private static final By CREATE_NEW_TAG_BUTTON = By.id("assign-new-tag");
    private static final By ASSIGN_EXISTING_TAG_BUTTON = By.id("assign-existing-tag");
    private static final By SELECT_TAG_DROPDOWN = By.id("select-tag");
    String assignedTags = "//p[@class = 'assigned-tags clearfix']//span//a[contains(text(), '%s')]";


    public EntryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public EntryPage openPage() {
        Assert.assertTrue(driver.findElement(CKE_PANEL_ID).isDisplayed(), "Page hasn't loaded");
        return this;
    }

    @Override
    public EntryPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CKE_PANEL_ID));
        Assert.assertTrue(driver.findElement(CKE_PANEL_ID).isDisplayed(), "Page hasn't loaded");
        return this;
    }

    public EntryPage writeText(String header, String text) {
        driver.findElement(BOLD_BUTTON).click();
        driver.findElement(TEXTBOX).sendKeys(header);
        driver.findElement(TEXTBOX).sendKeys(Keys.RETURN);
        driver.findElement(BOLD_BUTTON).click();
        driver.findElement(TEXTBOX).sendKeys(text);
        return this;

    }

    public FeedPage clickHome() {
        driver.findElement(HOME_BUTTON).click();
        FeedPage feedPage = new FeedPage(driver);
        feedPage.isPageOpened();
        return new FeedPage(driver);
    }

    public EntryPage writeTextToTag(String textTag) {
        driver.findElement(NEW_TAG_INPUT).click();
        driver.findElement(NEW_TAG_INPUT).sendKeys(textTag);
        return this;
    }

    public EntryPage clickOk() {
        driver.findElement(CREATE_NEW_TAG_BUTTON).click();
        return this;
    }

    public EntryPage checkThatTagIsVisibleInAssignTags(String textTag) {
        Assert.assertTrue(driver.findElement(By.xpath(String.format(assignedTags, textTag))).isDisplayed(), "There is no tag in the assigned tags field ");
        return this;
    }

    public EntryPage selectExistTag(String textExistTag) {
        Select tags = new Select(driver.findElement(SELECT_TAG_DROPDOWN));
        tags.selectByVisibleText(textExistTag);
        return this;
    }

    public EntryPage clickAssignedOk() {
        driver.findElement(ASSIGN_EXISTING_TAG_BUTTON).click();
        return this;
    }
}
