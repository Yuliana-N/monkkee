package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.FeedPage;
import pages.LoginPage;
import steps.LoginSteps;
import utils.CapabilitiesGenerator;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    private WebDriver driver;
    LoginPage loginPage;
    LoginSteps loginSteps;
    FeedPage feedPage;
    int timeOutSeconds = 20;

    @BeforeMethod
    public void setDriver(ITestContext context) {
        context.setAttribute("driver", driver);
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        driver.manage().timeouts().implicitlyWait(timeOutSeconds, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginSteps = new LoginSteps(driver);
        feedPage = new FeedPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }
}
