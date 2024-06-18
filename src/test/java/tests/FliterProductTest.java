
package tests;

import resources.Base;
import pageobjects.HomePage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FliterProductTest extends Base {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;


    @BeforeMethod
    public void setUp() throws FileNotFoundException, IOException {
        driver = initializedriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.flipkart.com/");

        homePage = new HomePage(driver, wait);
       
    }

    @Test
    public void FilterProduct() throws InterruptedException {
        homePage.searchForProduct("watches");
        Assert.assertTrue(homePage.issearchResultDisplayed());

        homePage.selectPriceFilter("1000");
        Assert.assertTrue(homePage.isFilterApplied());
    }


    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
