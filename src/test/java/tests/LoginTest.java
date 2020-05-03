package tests;

import io.qameta.allure.Link;
import models.User;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    @Test(description = "Login by valid user and password")
    @Link("https://my.monkkee.com/#/")
    public void login() {
        User user = new User("monkkee_bloger@mailinator.com", "QWERty123");
        loginSteps
                .login(user);
    }
}


