package testai;

import com.thoughtworks.xstream.XStream;
import models.ShopItem;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.CommonPage;
import page.SearchPage;

import java.io.File;
import java.io.IOException;

public class TestXMLReader extends BaseTest {
    ShopItem item;
    CommonPage commonPage;
    SearchPage searchPage;

    @Test
    public void testXMLReader () throws IOException {
        XStream xstream = new XStream();
        item = new ShopItem();
        commonPage = new CommonPage(driver);
        searchPage = new SearchPage(driver);

        item.setName("MacBook");
        item.setPrice("$602.00");
        item.setBrand("Apple");
        item.setProductCode("Product 16");

        FileUtils.writeStringToFile (new File("src/test/resources/MacBook.xml"), xstream.toXML(item));

        ShopItem MacBook = (ShopItem) xstream.fromXML(FileUtils.readFileToString(new File("src/test/resources/MacBook.xml")));

        commonPage.searchItem(MacBook.getName());
        Assert.assertSame(searchPage.getPriceOfFirstItem(),MacBook.getPrice(),"Price is not the same");
    }

}

