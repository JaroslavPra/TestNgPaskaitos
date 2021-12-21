package testai;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import page.CommonPage;
import page.ProductPage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class TestCartRemove extends BaseTest {

    CommonPage commonPage;
    ProductPage productPage;



    @Test
    public void testProductRemove() throws IOException {
        String cartTextFormat = "%s item(s)";

        commonPage = new CommonPage(driver);
        productPage = new ProductPage(driver);

        List<String> testData = getTestData("src/test/resources/ThreeItems");

        commonPage.clickDesktops();
        commonPage.clickShowAllDesktops();

        addProductToCart(testData);
        String actualCartText = commonPage.getTextCartTotal();
        String expectedCartText = String.format(cartTextFormat,testData.size());
        assertTrue(actualCartText.contains(expectedCartText), "Cart text does not contain expected text: "+expectedCartText);


        List<WebElement> removeElements = driver.findElements(By.xpath("//button[@title = 'Remove']"));
        removeProductsFromCar(removeElements);

        actualCartText = commonPage.getTextCartTotal();
        expectedCartText = String.format(cartTextFormat,0);
        assertTrue(actualCartText.contains(expectedCartText), "Cart text does not contain expected text: "+String.format(expectedCartText,0));




    }
    public void removeProductsFromCar(List<WebElement> list){
        for (WebElement button: list){
            commonPage.clickButtonCartTotal();
            commonPage.removeProductFromCart();
            waitForJS();
        }
    }

    public void waitForJS() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(new Function<>() {
            public Boolean apply(WebDriver driver) {
                return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
            }
        });
    }

    private void addProductToCart(List<String> list){

        for (int i = 0; i < list.size(); i++) {
            driver.findElement(By.xpath("//div[@class = 'product-thumb' and .//a[text() = '" + list.get(i) + "']]//button[.//span[text()='Add to Cart']]")).click();
            waitForJS();
        }
    }

    public List<String> getTestData(String fileName) throws IOException {
        List<String> records = new ArrayList<String>();
        String record;

        try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            while ((record = file.readLine()) != null) {
                records.add(record);
            }
        }
        return records;
    }
}
