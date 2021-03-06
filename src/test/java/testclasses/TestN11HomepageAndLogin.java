package testclasses;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opentest4j.AssertionFailedError;
import util.ReturnHTPPResponseCode;

import static io.restassured.RestAssured.given;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestN11HomepageAndLogin extends TestBase {

    @Test
    @Order(1)
    public void httpStatusCodeShouldBe200() throws Exception {
        logger.info("Checking response http status code..");
        ReturnHTPPResponseCode returnHTPPResponseCode = new ReturnHTPPResponseCode();
        try {
            Assertions.assertEquals(200, returnHTPPResponseCode.checkLink(jsonFileParser.parseJsonFileAndReturnRequestedDAta("n11.com", "baseUrl")));
        } catch (AssertionFailedError e) {
            logger.error("Can not reach homepage: ", e);
            throw e;
        }
    }

    @Test
    @Order(2)
    public void titleShouldBe() throws Exception {
        try {
            logger.info("Navigating to n11.com homepage..");
            n11HomepagePO.navigateToHomepage();
            logger.info("n11.com homepage title: ");
            logger.info(driver.getTitle());
            Assertions.assertEquals(n11HomepagePO.homePageTitle, n11HomepagePO.retrieveTitle());    //Also since get() is used instead of navigate() to go to URL, that provides verification too
        } catch (AssertionFailedError e) {
            logger.error("Can not navigate to n11.com:", e);
            throw e;
        }
    }

    @Test
    @Order(3)
    public void shouldClickKvkkMessage() throws Exception {
        try {
            logger.info("Clicking KVKK pop-up..");
            n11HomepagePO.clickTamamKVKKMessage();
            Thread.sleep(500);      //animation is slow, no need for explicit wait since it will cause troubles in this case
        } catch (AssertionFailedError e) {
            logger.error("Can not click KVKK pop-up:", e);
            throw e;
        }
    }

    @Nested
    class N11Login {
        @Test
        @Order(4)
        public void loginN11() throws Exception {
            driver.navigate().to("https://www.n11.com/giris-yap");
            n11HomepagePO.clickLoginButton();
            Thread.sleep(500);      //to give time to js to load
            n11LoginPO.clickEmailField();
            Thread.sleep(50);
            n11LoginPO.enterEmail();
            Thread.sleep(50);
            n11LoginPO.clickPasswordField();
            Thread.sleep(50);
            n11LoginPO.enterPassword();
            Thread.sleep(50);
            n11LoginPO.clickLogin();
            Thread.sleep(50);
            try {
                WebDriverWait wait = new WebDriverWait(driver, 20);
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@href='//www.n11.com/hesabim'][contains(.,'Eren Demirel')]"))));
                logger.info("Trying log in..");
                Assertions.assertTrue(driver.getTitle() == n11HomepagePO.homePageTitle);
                Assertions.assertEquals(n11LoginPO.retreiveLoggedInName(), jsonFileParser.parseJsonFileAndReturnRequestedDAta("n11.com", "fullName"));

            } catch (AssertionFailedError e) {
                logger.error("Login failed:", e);
                throw e;
            }
        }
    }
}
