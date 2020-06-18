package testclasses;

import dataProvider.JsonFileParser;
import org.junit.jupiter.api.*;
import org.opentest4j.AssertionFailedError;
import util.WriteToFile;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestN11SearchAndFavorite extends TestBase {

    String nameOftheProductOnSearchResults;
    String nameOftheProductOnFavoritesPage;

    @Test
    @Order(1)
    public void shouldSearch() throws Exception {
        logger.info("n11.com: Initiating product search..");
        logger.info("Navigating to the homepage..");
        n11HomepagePO.navigateToHomepage();
        logger.info("Clicking KVKK message..");
        n11HomepagePO.clickTamamKVKKMessage();
        Thread.sleep(1000);     //wait for animation to disappear
        logger.info("Clicking search bar..");
        n11HomepagePO.clickSearchBar();
        logger.info("Typing into search bar..");
        n11HomepagePO.typeIntoSearchBar("samsung");
        logger.info("Clicking search..");
        n11HomepagePO.clickSearch();
        Thread.sleep(2000);     //Because page is overloaded, put before explicit wait
    }

    @Test
    @Order(2)
    public void shouldGoToPageTwoInResultsPage() throws Exception {
        logger.info("Clicking the second page of search results.. ");
        n11SearchResultsPO.clickPageTwo();
        n11SearchResultsPO.waitUntilSecondPageIsLoaded();
    }

    @Test
    @Order(3)
    public void verifySearchPageTwo() throws Exception {
        logger.info("Verifying the second page of search results.. ");
        try {
            Assertions.assertTrue(n11SearchResultsPO.verifySearchPageTwo());
        } catch (AssertionFailedError e) {
            logger.error("Can not go to the page 2 for the search results:", e);
            throw e;
        }
    }

    @Test
    @Order(4)
    public void shouldClickOnFavoritesOfTheThirdPoductAndPrintTheNameOfTheProduct() throws Exception {
        logger.info("Clicking on favorite for the third product..");
        n11SearchResultsPO.clickFavorilereEkleThirdResult();
        WriteToFile writeToFile = new WriteToFile();
        writeToFile.writeStr(n11SearchResultsPO.retreiveTheNameOfThirdProduct());
        nameOftheProductOnSearchResults = n11SearchResultsPO.retreiveTheNameOfThirdProduct();
    }


    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class N11Favorites {


        @Test
        @Order(5)
        public void goToFavorites() throws InterruptedException {
            logger.info("Navigating to favorites..");
            n11HomepagePO.hoverOverHesabimMenu();
            n11HomepagePO.clickFavorilerimMenuItem();
            n11FavoritesPrePagePO.clickFavoritedProduct();
        }

        @Test
        @Order(6)
        public void checkFavorites() throws InterruptedException {
            logger.info("Checkinf whether the items are the same..");
            nameOftheProductOnFavoritesPage = n11FavoritesPO.getItemName();
            try {
                Assertions.assertEquals(nameOftheProductOnFavoritesPage, nameOftheProductOnSearchResults);
            } catch (AssertionFailedError e) {
                logger.error("Items aren't the same:", e);
                throw e;
            }
        }

        @Test
        @Order(7)
        public void removeItemFromFavorites() throws InterruptedException {
            logger.info("Removing the product from favorites..");
            n11FavoritesPO.removeTheItemFromFavorites();
            n11FavoritesPO.closeNotification();
        }

        @Test
        @Order(8)
        public void verifyEmptyFavorites() throws InterruptedException {
            logger.info("Verifying if the favorites is empty..");
            n11FavoritesPO.verifyEmptyFavorites();
        }

    }
}
