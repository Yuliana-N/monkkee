package tests;

import io.qameta.allure.Description;
import models.User;
import org.testng.annotations.Test;

public class EntryTest extends BaseTest {
    String header = "Заголовок";
    String text = "Тело";
    String email = "monkkee_bloger@mailinator.com";
    String password = "QWERty123";
    String textTag = "row";
    String textExistTag = "animals";


    @Test(priority = 3, description = "Проверка добавления нового тега к записи")
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
                .goToFeedPage();
    }

    @Test(priority = 4, description = "Проверка добавления существующего тега к записи")
    @Description("Проверка добавления существующего тега к записи")
    public void checkAddingExistingTag() {
        User user = new User(email, password);
        loginSteps
                .login(user);
        feedSteps
                .openPage();
        entrySteps
                .createEntry(header, text)
                .selectExistingTag(textExistTag)
                .checkExistingTagHasAdded(textExistTag)
                .goToFeedPage();
    }
}
