import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.nio.file.Watchable;
import java.util.concurrent.TimeUnit;

public class CareersPage {
    WebDriver driver;

    // Constructor to initialize WebDriver
    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

      By locationsBlock = By.xpath("//h3[@class='category-title-media ml-0' and normalize-space(text())='Our Locations']");
      By teamsBlock = By.xpath("//h3[@class='category-title-media' and normalize-space(text())='Find your calling']");
      By lifeAtInsiderBlock =By.xpath("//h2[@class='elementor-heading-title elementor-size-default' and normalize-space(text())='Life at Insider']");

    // Methods
    public void verifyLocationBlock() {

        WebElement locationElement = driver.findElement(locationsBlock);
        Assert.assertTrue(locationElement.isDisplayed(), "Location block is not displayed.");

    }

    public void verifyTeamsBlock() {


        WebElement teamsElement = driver.findElement(teamsBlock);
        Assert.assertTrue(teamsElement.isDisplayed(), "Teams block is not displayed.");
    }

    public void verifyLifeAtInsiderBlock() {
        WebElement lifeElement = driver.findElement(lifeAtInsiderBlock);
        Assert.assertTrue(lifeElement.isDisplayed(), "Life at Insider block is not displayed.");
    }



}
