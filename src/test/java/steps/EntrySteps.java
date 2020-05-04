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

    @Step("Creating new entry with Header='{Header}' Text='{Text}'")
    public EntrySteps createEntry(String Header, String Text) {
        feedPage
                .checkAmountOfEntries()
                .clickNewEntry();
        entryPage
                .isPageOpened()
                .writeText(Header, Text);
        return this;
    }

    @Step("Creating new tag on entry page")
    public EntrySteps createTag(String TextTag) {
        entryPage
                .writeTextToTag(TextTag);

        return this;
    }

    @Step("Adding new tag on entry and checking")
    public EntrySteps addTagAndCheck(String TextTag) {
        entryPage
                .clickOk()
                .checkThatTagIsVisibleInAssignTags(TextTag);

        return this;
    }

    @Step("Adding existing tag on entry")
    public EntrySteps selectExistingTag(String TextExistTag) {
        entryPage
                .selectExistTag(TextExistTag)
                .clickAssignedOk();
        return this;
    }

    @Step("Checking adding existing tag")
    public EntrySteps checkExistingTagHasAdded(String TextExistTag) {
        entryPage
                .checkThatTagIsVisibleInAssignTags(TextExistTag);
        return this;
    }

    @Step("Going to feed page and check amount of entries")
    public EntrySteps goToFeedPage() {
        entryPage
                .clickHome();
        feedPage
                .checkAmountOfEntriesAfterActionOnEntries(1);
        return this;
    }
}
