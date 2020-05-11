package tests;

import io.qameta.allure.Link;
import models.User;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    @Test(priority = 1, description = "Авторизация на сайте")
    @Link("https://my.monkkee.com/#/")
    public void login() {
        User user = new User("monkkee_bloger@mailinator.com", "QWERty123");
        loginSteps
                .login(user);
    }
}


