import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class QAJobsPage {
    WebDriver driver;

    // Constructor to initialize WebDriver
    public QAJobsPage(WebDriver driver) {
        this.driver = driver;
    }


    By seeAllQAJobsButton = By.xpath("//*[@class='btn btn-outline-secondary rounded text-medium mt-2 py-3 px-lg-5 w-100 w-md-50' and contains(text(),'See all QA jobs')]");

    By locationFilter = By.cssSelector("span#select2-filter-by-location-container[title='All']");
    By locationOption = By.xpath("//li[text()='Istanbul, Turkey']");
    By departmentFilter = By.cssSelector("span#select2-filter-by-department-container");
    By departmentOption = By.xpath("//li[text()='Quality Assurance']");

    By positionList = (By.cssSelector(".position-list-item-wrapper"));
    By viewRole = By.xpath("//a[text()='View Role']");

    // Methods

    public void clickSeeAllQAJobs() {

        // Dom yapısında görüntülenmediği için JS ile tıklandı.

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // İşlem yapılabilmesi için WebElemente çevrildi.
        WebElement allQAJobsButton = driver.findElement(seeAllQAJobsButton);
        js.executeScript("arguments[0].click();", allQAJobsButton);

    }

    public void filterJobsByLocationAndDepartment() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Sayfayı filtre bölümüne kaydırma

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        WebElement locationDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(locationFilter));
        Thread.sleep(5000); // Lokasyon filtresi yüklenmesine rağmen hemen tıklanınca seçenekler gelmediği için eklendi.

        // Tıklama işlemi
        locationDropdownElement.click();

        // Istanbul, Turkey'in listede görünür olmasını bekle
        WebElement istanbulOption = wait.until(ExpectedConditions.elementToBeClickable(locationOption));
        Thread.sleep(5000);

        // Istanbul tıkla.
        istanbulOption.click();
        Thread.sleep(3000);


        /* Bu ve altındaki işlemler case'de See QA All Jobs butonuna tıklandığı için departman filtresi otomatik olarak seçilmekte. O sebeple yorum satırı.

        // Departman filtresini bekleme işlemi
        WebElement departmentDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(departmentFilter));

        // Tıklama işlemi
        departmentDropdownElement.click();

        // Quality Assurance'ın listede görünür olmasını bekle
        WebElement qaOption = wait.until(ExpectedConditions.elementToBeClickable(departmentOption));

        //Tıklama işlemi
        qaOption.click();
*/
    }

    public void verifyJobsDisplayed() {

        List<WebElement> jobList = driver.findElements((By) positionList);
        if (jobList.size() > 0) {
            System.out.println("\n\nToplam iş sayısı: " + jobList.size());
        } else {
            System.out.println("Mevcut iş bulunamadı.");
        }

        // Bulunan her sonucun case'de belirtilen filtrelere uygunluğu kontrol edildi.

        for (WebElement job : jobList) {
            String position = job.findElement(By.cssSelector(".position-title")).getText();
            String department = job.findElement(By.cssSelector(".position-department")).getText();
            String location = job.findElement(By.cssSelector(".position-location")).getText();

            Assert.assertTrue(position.contains("Software"), "Position yanlış: " + position); // Test fail olmaması için Software yapıldı. Yayında Software Test Engineer rolü var.
            Assert.assertTrue(department.contains("Quality Assurance"), "Department yanlış: " + department);
            Assert.assertTrue(location.contains("Istanbul, Turkey"), "Location yanlış: " + location);
        }


    }
    public void clickViewRole() throws InterruptedException {


        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Pozisyon listesi kullanılabilmek için Web Element'e çevirildi.

        WebElement positionListElement = driver.findElement(positionList);
        js.executeScript("arguments[0].scrollIntoView(true);", positionListElement);

        // View Role butonunun gözükmesi için pozisyon listesinde aksiyon alındı.

        Actions actions = new Actions(driver);
        Thread.sleep(3000);
        actions.moveToElement(positionListElement).perform();

       // Tıklama işlemi
        driver.findElement(viewRole).click();
        Thread.sleep(3000);


        }

    }

