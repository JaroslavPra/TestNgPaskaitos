package testai;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeSuite
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }
    @BeforeMethod
    public void openHomePage(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.opencart.com/");
    }
    @AfterMethod
    public static void closeBrowser(){
        driver.close();
    }

    @AfterSuite
    public static void quiteDriver(){
        driver.quit();
    }
}
