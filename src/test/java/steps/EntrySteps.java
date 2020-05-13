package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.EntryPage;
import pages.FeedPage;

public class EntrySteps {
    private EntryPage entryPage;
    private FeedPage feedPage;

    public EntrySteps(WebDriver driver) {
        entryPage = new EntryPage(driver);
        feedPage = new FeedPage(driver);
    }

    @Step("Creating new entry with parameters header:'{header}' and text:'{text}'")
    public EntrySteps createEntry(String header, String text) {
        feedPage
                .checkAmountOfEntries()
                .clickNewEntry();
        entryPage
                .isPageOpened()
                .writeText(header, text);
        return this;
    }

    @Step("Creating new tag with random name : '{textTag}' on entry page ")
    public EntrySteps createTag(String textTag) {
        entryPage
                .writeTextToTag(textTag);

        return this;
    }

    @Step("Adding new tag with random name : '{textTag}' on entry and checking")
    public EntrySteps addTagAndCheck(String textTag) {
        entryPage
                .clickOk()
                .checkThatTagIsVisibleInAssignTags(textTag);

        return this;
    }

    @Step("Adding existing tag on entry")
    public EntrySteps selectExistingTag(String textExistTag) {
        entryPage
                .selectExistTag(textExistTag)
                .clickAssignedOk();
        return this;
    }

    @Step("Checking adding existing tag")
    public EntrySteps checkExistingTagHasAdded(String textExistTag) {
        entryPage
                .checkThatTagIsVisibleInAssignTags(textExistTag);
        return this;
    }

    @Step("Going to feed page")
    public EntrySteps goToFeedPage() {
        entryPage
                .clickHome();
        return this;
    }

    @Step("Check amount of entries")
    public EntrySteps checkAmountOfEntries() {
        feedPage
                .checkAmountOfEntriesAfterAddingEntry();
        return this;
    }
}
