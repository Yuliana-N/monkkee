package pages;

import lombok.extern.log4j.Log4j2;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

import java.util.List;

@Log4j2
public class LoginPage extends BasePage {

    private static final By CANCEL_BUTTON = By.xpath("//*[text() = 'Cancel']");

    @FindBy(id = "login")
    WebElement userField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(css = ".btn-text-content")
    WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage isPageOpened() {
        log.info("Login page opened");
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return this;
    }

    public LoginPage openPage() {
        log.info("Opening LoginPage page");
        driver.get("https://my.monkkee.com/");
        isPageOpened();
        return this;
    }

    public LoginPage writeEmail(String email) {
        userField.sendKeys(email);
        return this;
    }

    public LoginPage writePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public FeedPage clickLogin() {
        loginButton.click();
        List<WebElement> modal = driver.findElements(By.cssSelector(".modal"));
        log.info("Checking that \"Feed the monkkee\" modal is opened or not");
        if(modal.size() > 0){
            log.info("\"Feed the monkkee\" modal is opened");
            wait.until(ExpectedConditions.elementToBeClickable(CANCEL_BUTTON));
            log.info("Closing \"Feed the monkkee\" modal and login");
            driver.findElement(CANCEL_BUTTON).click();
        }
        FeedPage entries = new FeedPage(driver);
        entries.isPageOpened();
        return new FeedPage(driver);
    }

    public void login(User user) {
        writeEmail(user.getEmail());
        writePassword(user.getPassword());
        clickLogin();
        log.info("Login with user" + user.getEmail() + "and password" + user.getPassword());
        AllureUtils.takeScreenshot(driver);
    }
}