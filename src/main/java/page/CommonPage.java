package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonPage extends AbstractObjectPage {
    @FindBy(linkText = "Desktops")
    private WebElement linkDesktops;

    @FindBy(linkText = "Show All Desktops")
    private WebElement linkShowAllDesktops;

    @FindBy(id = "cart-total")
    private WebElement buttonCartTotal;

    @FindBy(xpath = "//button[@title = 'Remove']")
    private WebElement buttonProductRemoveFromCart;

    @FindBy(name = "search")
    private WebElement searchBox;


    public CommonPage(WebDriver driver) {
        super(driver);
    }

    public void clickDesktops(){
        linkDesktops.click();
    }

    public void clickShowAllDesktops(){
        linkShowAllDesktops.click();
    }

    public void clickButtonCartTotal (){
        buttonCartTotal.click();
    }

    public String getTextCartTotal (){
        return buttonCartTotal.getText();
    }

    public void removeProductFromCart(){
        buttonProductRemoveFromCart.click();
    }

    public void searchItem(String searchItem){
        searchBox.sendKeys(searchItem, Keys.ENTER);
    }

}
