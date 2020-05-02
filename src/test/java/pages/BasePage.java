package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    int timeOutSeconds = 20;

    BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, timeOutSeconds);
    }
    public abstract BasePage openPage();
    public abstract BasePage isPageOpened();
    }
