package webdriver;

import graphql.schema.SchemaElementChildrenContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    WebElement drivers;
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
        driver.get("https://demo.nopcommerce.com/register");

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
       driver.findElement(By.id("FirstName")).sendKeys("Tom");
    }

    @Test
    public void TC_02_Class() {
      driver.findElement(By.className("header-logo"));
    }


    @Test
    public void TC_03_Name() {
       driver.findElement(By.name("DateOfBirthDay"));
    }

    @Test
    public void TC_04_Tagname() {
        driver.findElement(By.tagName("input"));
    } // ten the

    @Test
    public void TC_05_LinkText() {
        // Do chinh xac cao, tuyet doi vi lay toan bo text chứa link
      driver.findElement(By.linkText("Shipping & returns"));
    }

    @Test
    public void TC_06_Partial_LinkText() {
        //Do chinh xac khong cao vì la tuong doi (lay 1 phan - dau/giua/cuoi)
        driver.findElement(By.partialLinkText("vendor account"));
        driver.findElement(By.partialLinkText("for vendor"));
        driver.findElement(By.partialLinkText("Apply for vendor"));
    }
    @Test
    public void TC_07_CSS() {
      // CSS va ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
     //   driver.findElement(By.cssSelector("input[id=FirstName]"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

       // CSS va Class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));

        // CSS va name
        driver.findElement(By.cssSelector("input[name='FirstName']"));

        // CSS va tagname
        driver.findElement(By.cssSelector("input"));

        // CSS va link, phai lam viec voi attribute
        driver.findElement(By.cssSelector("a[href='/customer/addresses']"));

        //CSS va partial link
        driver.findElement(By.cssSelector("a[href*='addresses']")); // Lay giua
     //   driver.findElement(By.cssSelector("a[href^='video.htm']")); // Lay dau
     //   driver.findElement(By.cssSelector("a[href$='video.htm']")); // Lay duoi

    }
    // Dac thu Xpath la khong cho viet tat
    @Test
    public void TC_08_XPath(){
        // XPath va ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        // XPath va Class
        driver.findElement(By.xpath("//div[@class='page-title']"));

        // XPath va name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        // XPath va tagname
        driver.findElement(By.xpath("//input"));

        // XPath va link, phai lam viec voi attribute
        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));
//a[@href='/customer/addresses']
        driver.findElement(By.xpath("//a[text()='Addresses']"));
      //  Thread.sleep(3000);

        //XPath va partial link
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]"));

        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));

    }
    @AfterClass
    public void afterClass() {
      //  driver.quit();
    }

}
