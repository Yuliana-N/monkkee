package tests;


import io.qameta.allure.Description;
import models.User;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    String text = "travel";
    String email = "monkkee_goodblogger@mailinator.com";
    String password = "QWERty123";


    @Test(priority = 7, description = "Проверка работы поиска по тексту")
    @Description("Проверка работы поиска по тексту")
    public void checkSearchByText() {
        User user = new User(email, password);
        loginSteps
                .login(user);
        searchModalSteps
                .openPage()
                .clickAndSearch(text)
                .createListOfEntriesAndCheckText(text);
    }
}