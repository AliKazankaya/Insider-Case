import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.util.concurrent.TimeUnit;

public class HomePage {
    WebDriver driver;

    // Constructor to initialize WebDriver
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    // Elements
    By companyMenu = By.cssSelector("div#navbarNavDropdown li:nth-child(6) > a#navbarDropdownMenuLink");
    By careersMenu = By.cssSelector("div#navbarNavDropdown div.new-menu-dropdown-layout-6-mid-container > a:nth-child(2)");


   public void clickCompanyMenu() {


       driver.findElement(companyMenu).click();

   }
    public void clickCareersMenu() {

        driver.findElement(careersMenu).click();
    }
}
