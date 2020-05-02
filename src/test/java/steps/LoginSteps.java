package steps;

import io.qameta.allure.Step;
import models.User;
import org.openqa.selenium.WebDriver;
import pages.EntriesPage;
import pages.LoginPage;

public class LoginSteps {
    private LoginPage loginPage;
    private EntriesPage entriesPage;

    public LoginSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        entriesPage = new EntriesPage(driver);
    }

    @Step("Login by user {user.email}")
    public void login(User user) {
        loginPage
                .openPage()
                .login(user);
        entriesPage
                .isPageOpened();

    }

}
