import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;



public class TestCase {

    WebDriver driver;
    HomePage homePage;
    CareersPage careersPage;
    QAJobsPage qaJobsPage;
    LeverAppPage leverAppPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");


        driver = new ChromeDriver(options);

        driver.get("https://useinsider.com");

        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        qaJobsPage = new QAJobsPage(driver);
        leverAppPage = new LeverAppPage(driver);
    }

    @Test(priority = 1)
    public void testHomePage() throws InterruptedException {
        // Test Step 1: Check if Insider home page is opened
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("useinsider.com"), "Home page is not opened.");

        homePage.clickCompanyMenu();
        homePage.clickCareersMenu();


    }

        @Test (priority = 2)
        public void testCareersPage()  {
            // Test Step 2: Navigate to Careers Page and verify the blocks

              careersPage.verifyLocationBlock();
              careersPage.verifyTeamsBlock();
              careersPage.verifyLifeAtInsiderBlock();

        }

        @Test (priority = 3)
        public void testQAJobsPage() throws InterruptedException {

            // Test Step 3: Go to QA Jobs, filter by location and department, and verify the job list
            driver.get("https://useinsider.com/careers/quality-assurance/");

            qaJobsPage.clickSeeAllQAJobs();

            qaJobsPage.filterJobsByLocationAndDepartment();

            qaJobsPage.verifyJobsDisplayed();


        }

        @Test (priority = 4)

         public void testLeverAppPage () throws InterruptedException {

         qaJobsPage.clickViewRole();
         leverAppPage.verifyLeverPage();

        }


        @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
