package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.EntryPage;
import pages.FeedPage;
import pages.LoginPage;
import pages.SearchModalPage;
import steps.EntrySteps;
import steps.FeedSteps;
import steps.LoginSteps;
import steps.SearchModalSteps;
import utils.CapabilitiesGenerator;
import utils.DataCreator;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    private WebDriver driver;
    LoginPage loginPage;
    FeedPage feedPage;
    EntryPage entryPage;
    SearchModalPage searchModalPage;
    LoginSteps loginSteps;
    FeedSteps feedSteps;
    EntrySteps entrySteps;
    SearchModalSteps searchModalSteps;
    int timeOutSeconds = 20;
    DataCreator data = new DataCreator();

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
        searchModalPage = new SearchModalPage(driver);
        searchModalSteps = new SearchModalSteps(driver);
        data = new DataCreator();
        context.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }
}
