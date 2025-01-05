import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Set;


public class LeverAppPage {

    WebDriver driver;

    public LeverAppPage(WebDriver driver) {
        this.driver = driver;

    }

    public void verifyLeverPage() throws InterruptedException {

        // Ekranları unique olarak tutma işlemi

        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

         // Windows ekranı ana ekran değilse değiştirme işlemi

         for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
           // Sayfa kontrolü

            String newPageUrl = driver.getCurrentUrl();
            System.out.println( " \nYeni sayfanın URL'si: " + newPageUrl);
            Assert.assertTrue(newPageUrl.contains("lever.co"), "Yönlendirilen sayfa Lever Application form sayfası değil. Mevcut URL: " + newPageUrl);


        }
    }

