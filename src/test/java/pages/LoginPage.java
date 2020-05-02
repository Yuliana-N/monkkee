package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

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
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return this;
    }

    public LoginPage openPage() {
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

    public EntriesPage ckickLogin() {
        loginButton.click();
        try{
        driver.findElement(By.cssSelector(".modal")).isDisplayed();
            wait.until(ExpectedConditions.elementToBeClickable(CANCEL_BUTTON));
            driver.findElement(CANCEL_BUTTON).click();
        }
        catch(NoSuchElementException e) {
            e.printStackTrace();
            }
        EntriesPage entries = new EntriesPage(driver);
        entries.isPageOpened();
        return new EntriesPage(driver);
    }

    public void login(User user) {
        writeEmail(user.getEmail());
        writePassword(user.getPassword());
        ckickLogin();
        AllureUtils.takeScreenshot(driver);
    }


}