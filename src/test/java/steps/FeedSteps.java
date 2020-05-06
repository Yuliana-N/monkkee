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

    @Step("Select entry with number {entryNumber}")
    public FeedSteps selectEntryByCheckCheckbox(int entryNumber) {
        feedPage
                .checkAmountOfEntries()
                .getListOfCheckBoxAndClickOneCheckbox(entryNumber);
        return this;
    }

    @Step("Deliting entry")
    public FeedSteps deleteEntry() {
        feedPage
                .clickDeleteButton()
                .clickAlert();
        return this;
    }

    @Step("Check amount of entries after deliting")
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

    @Step("Deliting all entries {AmountOfEntries}")
    public FeedSteps deleteAllEntries() {
        feedPage
                .clickDeleteButton()
                .clickAlert()
                .checkAmountOfEntriesIsNull();
        return this;
    }
}