package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * In this class, Strings are used to locate
 */
public class N11FavoritesPO {

    WebDriver driver;

    public N11FavoritesPO(WebDriver driver) throws Exception {
        this.driver = driver;
    }

    String item_Xpath = "//h3[@itemprop='name']";
    String silButton_Xpath = "//span[@class='deleteProFromFavorites']";
    String silindiNotificationMessage_Xpath = "//span[contains(.,'Ürününüz listeden silindi.')]";
    String notificationConfirmationButton_Xpath = "//span[contains(.,'Tamam')]";
    String emptyFavoritesListIdentifier_Xpath = "//div[@class='emptyWatchList hiddentext'][contains(.,'İzlediğiniz bir ürün bulunmamaktadır.')]";

    public String getItemName() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(item_Xpath))));
        return driver.findElement(By.xpath(item_Xpath)).getText();
    }

    public void removeTheItemFromFavorites(){
        driver.findElement(By.xpath(silButton_Xpath)).click();
    }

    public void closeNotification(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(silindiNotificationMessage_Xpath))));
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath(notificationConfirmationButton_Xpath))).click().perform();
    }

    public WebElement verifyEmptyFavorites(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(silindiNotificationMessage_Xpath))));
    }

}
