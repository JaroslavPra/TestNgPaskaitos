package testai;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.CommonPage;
import page.SearchPage;

public class SearchBasicTest extends BaseTest{

    CommonPage commonPage;
    SearchPage searchPage;

    @Test(dataProvider = "products")
    public void testSearchProducts(String productName){
        commonPage = new CommonPage(driver);
        searchPage = new SearchPage(driver);
        commonPage.searchItem(productName);

        Assert.assertSame(searchPage.getNameOfFirstItem(),productName, "Product"+productName+"Did not match with found product name");
        Assert.assertNotEquals(searchPage.numberOfFoundItems(),0,"Did not found any items with name: "+productName);
    }

    @DataProvider
    public Object[][] products()  {
        return new Object[][]{
                {"iPod Nano"},
                {"MacBook"},
                {"Canon EOS 5D"},
                {"Random Product Name"}
        };
    }
}
