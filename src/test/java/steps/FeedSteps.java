package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.EntryPage;
import pages.FeedPage;

public class FeedSteps {


    private FeedPage feedPage;
    private EntryPage entryPage;

    public FeedSteps(WebDriver driver) {
        feedPage = new FeedPage(driver);
        entryPage = new EntryPage(driver);
    }

    @Step("Opening page and checking that page has been opened")
    public FeedSteps openPage() {
        feedPage
                .openPage()
                .isPageOpened();
        return this;
    }

    @Step("Select entry ")
    public FeedSteps selectEntryByCheckCheckbox() {
        feedPage
                .checkAmountOfEntries()
                .clickOneCheckbox();
        return this;
    }

    @Step("Deleting entry")
    public FeedSteps deleteEntry() {
        feedPage
                .clickDeleteButton()
                .clickAlert();
        return this;
    }

    @Step("Check amount of entries after deleting")
    public FeedSteps checkAmountOfEntriesAfterDeliting() {
        feedPage
                .checkAmountOfEntriesAfterDeleteOneEntry();
        return this;
    }

    @Step("Select all entries")
    public FeedSteps getAllEntryByCheckCheckbox() {
        feedPage
                .ckickAllEntriesCheckbox();
        return this;
    }

    @Step("Deleting all entries '{AmountOfEntries}'")
    public FeedSteps deleteAllEntries() {
        feedPage
                .clickDeleteButton()
                .clickAlert();
        return this;
    }

    @Step("Check amount of entries after deleting all entries")
    public FeedSteps checkAmountOfEntriesAfterDeletingAllEntries() {
        feedPage
                .checkAmountOfEntriesIsNull();
        return this;
    }

    @Step("Check that tag tag with name = '{textTag}' is on the entry on feed page")
    public FeedSteps checkThatTagOnTheEntry(String textTag) {
        feedPage
                .findLastEntryAndCheckThatTagOnTheEntry(textTag);
        return this;
    }
}