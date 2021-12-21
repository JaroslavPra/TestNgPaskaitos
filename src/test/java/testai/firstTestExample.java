package testai;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

public class firstTestExample {
    //public static void main (String[] args) {

    private static WebDriver driver;

    @BeforeTest (groups = {"LoginPageTesting", "DesktopsTesting", "RegisterPageTesting"})
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.opencart.com");
    }

    @BeforeMethod (groups = {"LoginPageTesting", "DesktopsTesting", "RegisterPageTesting"})
    public void beforeMethod(){
        System.out.println("New Test Method starting");
    }
    @AfterMethod (groups = {"LoginPageTesting", "DesktopsTesting", "RegisterPageTesting"})
    public void afterMethod(){
        System.out.println("Test Method done");
    }
    @Test (groups = {"LoginPageTesting"})
    public void testMethod1() {
        driver.findElement(By.id("wishlist-total")).click();

    }

    @Test (groups = {"LoginPageTesting"})
    public void testMethod2() {
        Assert.assertEquals(driver.findElements(By.name("search")).size(), 1, "Number of search boxes: ");
        //System.out.println(driver.findElements(By.name("search")).size());
    }

    @Test (groups = {"LoginPageTesting"})
    public void testMethod3()  {
        WebElement emailElement = driver.findElement(By.id("input-email"));
        //driver.findElement(By.xpath("//div[@id=\"input-email\"]")).sendKeys("email@email.com");
        //driver.findElement(By.xpath("//div[@id=\"input-email\"]")).clear();
        //driver.findElement(By.xpath("//div[@id=\"input-email\"]")).submit();
        emailElement.sendKeys("email@email.com");
        emailElement.clear();
        emailElement.submit();
        driver.findElement(By.xpath("//div[@id=\"content\"]/div/div[1]/div/a")).click();
    }

    @Test (groups = {"RegisterPageTesting"})
    public void testMethod4() {
        Assert.assertTrue(driver.findElement(By.id("input-password")).isEnabled(), "Password field is enabled: ");
        //System.out.println(driver.findElement(By.id("input-password")).isEnabled());
    }

    @Test (groups = {"RegisterPageTesting"})
    public void testMethod5() {
        Assert.assertTrue(driver.findElement(By.id("cart")).isDisplayed(), "Cart element is displayed: ");
        //System.out.println(driver.findElement(By.id("cart")).isDisplayed());
    }

    @Test (groups = {"RegisterPageTesting"})
    public void testMethod6() {
        Assert.assertFalse(driver.findElement(By.name("agree")).isSelected(), "Agree check box is not selected: ");
        //System.out.println(driver.findElement(By.name("agree")).isSelected());
    }

    @Test (groups = {"DesktopsTesting"})
    public void testMethod7() {
        driver.findElement(By.linkText("Desktops")).click();
        driver.findElement(By.linkText("Show All Desktops")).click();
        new Select(driver.findElement(By.id("input-limit"))).selectByVisibleText("25");
        Select newDropDownElement = new Select(driver.findElement(By.id("input-limit")));

        Assert.assertSame(newDropDownElement.getFirstSelectedOption().getText(), "25", "Selected items price is 25: ");
        //System.out.println(newDropDownElement.getFirstSelectedOption().getText());
        newDropDownElement.selectByIndex(3);
        newDropDownElement = new Select(driver.findElement(By.id("input-limit")));
        System.out.println(newDropDownElement.getFirstSelectedOption().getText());
    }

    @AfterTest (groups = {"LoginPageTesting", "DesktopsTesting", "RegisterPageTesting"})
    public void driverShutDown() {
        driver.quit();
    }

    //}
}
