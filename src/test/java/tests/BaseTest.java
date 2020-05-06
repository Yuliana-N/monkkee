package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.EntryPage;
import pages.FeedPage;
import pages.LoginPage;
import steps.EntrySteps;
import steps.FeedSteps;
import steps.LoginSteps;
import utils.CapabilitiesGenerator;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    private WebDriver driver;
    LoginPage loginPage;
    FeedPage feedPage;
    EntryPage entryPage;
    LoginSteps loginSteps;
    FeedSteps feedSteps;
    EntrySteps entrySteps;
    int timeOutSeconds = 20;

    @BeforeMethod
    public void setDriver(ITestContext context) {
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        driver.manage().timeouts().implicitlyWait(timeOutSeconds, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        feedPage = new FeedPage(driver);
        entryPage = new EntryPage(driver);
        loginSteps = new LoginSteps(driver);
        feedSteps = new FeedSteps(driver);
        entrySteps = new EntrySteps(driver);
        context.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }
}
