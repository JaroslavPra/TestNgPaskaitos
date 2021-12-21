package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends AbstractObjectPage{

    public SearchPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@id='content']/div[3]/div[1]/div/div[2]/div[1]/p[2]/text()")
    WebElement firstItemPrice;

    @FindBy(className = "product-thumb")
    List<WebElement> itemsFound;

    @FindBy(xpath = "//*[@id='content']/div[3]/div[1]/div/div[2]/div[1]/h4/a")
    WebElement firstItemName;

    public int numberOfFoundItems (){
       return itemsFound.size();
    }

    public String getPriceOfFirstItem() {
        return firstItemPrice.getText();
    }

    public String getNameOfFirstItem(){
        return firstItemName.getText();
    }





}
