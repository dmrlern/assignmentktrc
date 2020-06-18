package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * In this class, Strings are used to locate
 */
public class N11FavoritesPrePagePO {

    WebDriver driver;

    public N11FavoritesPrePagePO(WebDriver driver) throws Exception {
        this.driver = driver;
    }

    String favoritedItem_Xpath = "//img[@width='80']";

    public void clickFavoritedProduct() {
        driver.findElement(By.xpath(favoritedItem_Xpath)).click();
        //Wait until the new page is loaded
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(.,'Sepete Ekle')]"))));
    }
}
