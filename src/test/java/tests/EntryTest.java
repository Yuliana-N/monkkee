package tests;

import io.qameta.allure.Description;
import models.User;
import org.testng.annotations.Test;

public class EntryTest extends BaseTest {
    String textExistTag;
    String header = data.getFakeHeader();
    String text = data.getFakeEntryText();
    String email = "monkkee_bloger@mailinator.com";
    String password = "QWERty123";
    String textTag = data.getFakeTag();


    @Test(priority = 4, description = "Проверка добавления нового тега к записи")
    @Description("Проверка добавления нового тега к записи")
    public void checkAddingNewTag() {
        User user = new User(email, password);
        loginSteps
                .login(user);
        feedSteps
                .openPage();
        entrySteps
                .createEntry(header, text)
                .createTag(textTag)
                .addTagAndCheck(textTag)
                .goToFeedPage()
                .checkAmountOfEntries();
        feedSteps
                .checkThatTagOnTheEntry(textTag);
    }

    @Test(priority = 5, description = "Проверка добавления существующего тега к записи '{textExistTag}'")
    @Description("Проверка добавления существующего тега к записи")
    public void checkAddingExistingTag() {
        User user = new User(email, password);
        loginSteps
                .login(user);
        feedSteps
                .openPage();
        entrySteps
                .createEntry(header, text);
        textExistTag = entryPage.getRandomElement();
        entrySteps
                .selectExistingTag(textExistTag)
                .checkExistingTagHasAdded(textExistTag)
                .goToFeedPage();
        feedSteps
                .checkThatTagOnTheEntry(textExistTag);
    }
}
