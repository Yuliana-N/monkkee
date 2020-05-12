package tests;


import io.qameta.allure.Description;
import models.User;
import org.testng.annotations.Test;

public class FeedTest extends BaseTest {

    String header = data.getFakeHeader();
    String text = data.getFakeEntryText();
    String email = "monkkee_bloger@mailinator.com";
    String password = "QWERty123";

    @Test(priority = 2, description = "Проверка добавления записи в дневник ")
    @Description("Проверка добавления записи в дневник")
    public void checkAddingEntry() {
        User user = new User(email, password);
        loginSteps
                .login(user);
        feedSteps
                .openPage();
        entrySteps
                .createEntry(header, text)
                .goToFeedPage()
                .checkAmountOfEntries();
    }

    @Test(priority = 3, description = "Проверка удаления записи из дневника")
    @Description("Проверка удаления записи из дневника")
    public void checkDeletingEntry() {
        User user = new User(email, password);
        loginSteps
                .login(user);
        feedSteps
                .openPage()
                .selectEntryByCheckCheckbox()
                .deleteEntry()
                .checkAmountOfEntriesAfterDeliting();
    }

    @Test(priority = 6, description = "Проверка удаления всех записей из дневника")
    @Description("Проверка удаления всех записей из дневника")
    public void checkDeletingAllEntries() {
        User user = new User(email, password);
        loginSteps
                .login(user);
        feedSteps
                .openPage()
                .getAllEntryByCheckCheckbox()
                .deleteAllEntries()
                .checkAmountOfEntriesAfterDeletingAllEntries();
    }
}
