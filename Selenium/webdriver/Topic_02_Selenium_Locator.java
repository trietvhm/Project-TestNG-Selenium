package webdriver;

import graphql.schema.SchemaElementChildrenContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    // khoi tao trinh duyet san
    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.techpanda.org/index.php/customer/account/create/");

    }
     // Luonng testcase s chay tu tren xuong duoi (tinh nang cua TESTNG)
    // TestNG: order test case theo Alphabet (0-9 A-Z)

    // First name - HTML code
    // Structure of HTML element: <tagname attribute_name_1 = 'attribute_value' attribute_name_2 = 'attribute_value'>
    // quan tâm element này co atribute gi va cai nao la duy nhat
    //uu tien ??? nen dung cai nao de bat element

    /*
    <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName" value="">
     */

    //khi minh thay mot id va id do la duy nhat thi minh dung TC_01
  //  FirefoxDriver firefoxDriver = new FirefoxDriver();
 //   Select select = new Select(firefoxDriver.findElement(By.className("")));
    @Test
    public void TC_01_ID() {
        // dung bien driver tim element co id la firstname
       driver.findElement(By.id("firstname")).sendKeys("Tom");

    }

    @Test
    public void TC_02_Class() {
      driver.findElement(By.className("page-header-container"));
    }


    @Test
    public void TC_03_Name() {
       driver.findElement(By.name("success_url"));
    }

    @Test
    public void TC_04_Tagname() {
        driver.findElement(By.tagName("input"));
    }

    @Test
    public void TC_05_LinkText() {
        // Do chinh xac cao, tuyet doi vi lay toan bo
      driver.findElement(By.linkText("ABOUT US"));
    }

    @Test
    public void TC_06_Partial_LinkText() {
        //Do chinh xac khong cao vì la tuong doi (lay 1 phan - dau/giua/cuoi)
        driver.findElement(By.partialLinkText("US"));
    }
    @Test
    public void TC_07_CSS() {
      // CSS va ID
        driver.findElement(By.cssSelector("input[id='firstname']"));
        driver.findElement(By.cssSelector("input#firstname"));
        driver.findElement(By.cssSelector("#firstname"));

       // CSS va Class
        driver.findElement(By.cssSelector("div[class='page-header-container']"));
        driver.findElement(By.cssSelector("div.page-header-container"));
        driver.findElement(By.cssSelector(".page-header-container"));

        // CSS va name
        driver.findElement(By.cssSelector("input[name='success_url']"));

        // CSS va tagname
        driver.findElement(By.cssSelector("input"));

        // CSS va link, phai lam viec voi attribute
        driver.findElement(By.cssSelector("a[href='http://live.techpanda.org/index.php/about-magento-demo-store/']"));

        //CSS va partial link
        driver.findElement(By.cssSelector("a[href*='about-magento-demo-store/']")); // Lay giua
     //   driver.findElement(By.cssSelector("a[href^='video.htm']")); // Lay dau
     //   driver.findElement(By.cssSelector("a[href$='video.htm']")); // Lay duoi

    }
    // Dac thu Xpath la khong cho viet tat
    @Test
    public void TC_08_XPath() throws InterruptedException {
        // XPath va ID
        driver.findElement(By.xpath("//input[@id='firstname']"));

        // XPath va Class
        driver.findElement(By.xpath("//div[@class='page-header-container']"));

        // XPath va name
        driver.findElement(By.xpath("//input[@name='success_url']"));

        // XPath va tagname
        driver.findElement(By.xpath("//input"));

        // XPath va link, phai lam viec voi attribute
        driver.findElement(By.xpath("//a[@href='http://live.techpanda.org/index.php/about-magento-demo-store/']"));
        Thread.sleep(3000);
        //driver.findElement(By.xpath("//a[text()='ABOUT US']"));
        Thread.sleep(3000);

        //XPath va partial link
        driver.findElement(By.xpath("//a[contains(@href,'about-magento-demo-store/')]")); // Lay giua
        driver.findElement(By.xpath("//a[contains(text(),'us')]")); // Lay dau
        //driver.findElement(By.xpath("a[href$='video.htm']")); // Lay duoi
    }
    @AfterClass
    public void afterClass() {
      //  driver.quit();
    }

}
