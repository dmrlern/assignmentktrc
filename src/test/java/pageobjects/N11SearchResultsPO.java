package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.ThreadLocalRandom;


/**
 * In this class, PageFactory and @FindBy annotation is not used, By is used to locate elements
 */
public class N11SearchResultsPO {
    WebDriver driver;

    public N11SearchResultsPO(WebDriver driver) throws Exception {
        this.driver = driver;
    }


    By pg2Button = By.xpath("//a[@href='https://www.n11.com/arama?q=samsung&pg=2'][contains(.,'2')]");
    By theThirdProductOnTheSecondPageOfSearchResults = By.xpath("(//h3[@class='productName '])[3]");
    By secondPageLoadIdentifier = By.xpath("(//img[@class='lazy'])[35]");
    By lastElementToLoad = By.xpath("(//img[@class='lazy'])[43]");
    By favorilereEkleButton = By.xpath("(//span[@title='Favorilere ekle'])[3]");

    public void clickPageTwo() throws Exception {
        Thread.sleep(500);       //page load, no problem with all threads suspended, no need to explicit wait since n11 is slow
        driver.findElement(pg2Button).click();

    }


    public boolean verifySearchPageTwo(){
        //Explicit wait until page is loaded
        WebElement secondPageLoadVerifier = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(secondPageLoadIdentifier));
        return secondPageLoadVerifier.isDisplayed();
    }


    public void waitUntilLastElementIsLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(lastElementToLoad));
    }

    public void waitUntilSecondPageIsLoaded(){

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(secondPageLoadIdentifier));
    }

    public void clickFavorilereEkleThirdResult(){
       /* Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(favorilereEkleButton)).click().perform();*/
       driver.findElement(favorilereEkleButton).click();
    }

    public String retreiveTheNameOfThirdProduct(){
        return driver.findElement(theThirdProductOnTheSecondPageOfSearchResults).getText();
    }


}
