package steps;

import io.qameta.allure.Step;
import models.User;
import org.openqa.selenium.WebDriver;
import pages.FeedPage;
import pages.LoginPage;

public class LoginSteps {
    private LoginPage loginPage;
    private FeedPage feedPage;

    public LoginSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        feedPage = new FeedPage(driver);
    }

    @Step("Login by user '{user.email}'")
    public void login(User user) {
        loginPage
                .openPage()
                .login(user);
        feedPage
                .isPageOpened();
    }
}
