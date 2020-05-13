package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Log4j2
public class EntryPage extends BasePage {

    private static final By CKE_PANEL_ID = By.id("cke_editable");
    private static final By TEXTBOX = By.id("editable");
    private static final By HOME_BUTTON = By.id("back-to-overview");
    private static final By BOLD_BUTTON = By.cssSelector(".cke_button__bold");
    private static final By NEW_TAG_INPUT = By.id("new-tag");
    private static final By CREATE_NEW_TAG_BUTTON = By.id("assign-new-tag");
    private static final By ASSIGN_EXISTING_TAG_BUTTON = By.id("assign-existing-tag");
    private static final By SELECT_TAG_DROPDOWN = By.id("select-tag");
    private static final By TAGS_IN_LIST = By.xpath("//*[@id = 'select-tag']/option");
    String assignedTags = "//p[@class = 'assigned-tags clearfix']//span//a[contains(text(), '%s')]";

    public EntryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public EntryPage openPage() {
        log.info("Opening Entry page");
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
        log.info("Write text to entry");
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
        log.info("Writing text to tag field " + textTag);
        driver.findElement(NEW_TAG_INPUT).sendKeys(textTag);
        return this;
    }

    public EntryPage clickOk() {
        driver.findElement(CREATE_NEW_TAG_BUTTON).click();
        return this;
    }

    public EntryPage checkThatTagIsVisibleInAssignTags(String textTag) {
        log.info("Checking that tag is visible in modal \"Assign tags\"");
        try {
            Assert.assertTrue(driver.findElement(By.xpath(String.format(assignedTags, textTag))).isDisplayed(), "There is no tag in the assigned tags field");
        } catch (NoSuchElementException ex) {
            ex.getStackTrace();
        }
        return this;
    }

    public EntryPage selectExistTag(String textExistTag) {
        Select tags = new Select(driver.findElement(SELECT_TAG_DROPDOWN));
        tags.selectByVisibleText(textExistTag);
        log.info("Selected a tag with a name " + textExistTag);
        return this;
    }

    public EntryPage clickAssignedOk() {
        driver.findElement(ASSIGN_EXISTING_TAG_BUTTON).click();
        return this;
    }

    public String getRandomElement() {
        List<WebElement> list = driver.findElements(TAGS_IN_LIST);
        log.info("Created a list of elements in the dropdown of existing tags with size: " + list.size());
        List<String> values = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < list.size(); i++) {
            String tag = list.get(i).getText();
            values.add(tag);
        }
        log.info("Created a list of names existing tags with size: " + values.size());
        int index = random.nextInt(values.size());
        String randomStringTag = values.get(index);
        log.info("Select random tag fronm list og tags: " + randomStringTag);
        return randomStringTag;
    }
}
