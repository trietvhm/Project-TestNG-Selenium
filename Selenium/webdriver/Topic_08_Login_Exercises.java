package webdriver;

import org.bouncycastle.asn1.cmp.Challenge;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Login_Exercises {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Empty_EmailPassword() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#pass")).clear();
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),"This is a required field.");

    }

    @Test
    public void TC_02_InvalidEmail() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("1234@1234.1234");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_LackPassword() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Incorrect() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456789");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");
    }

    @Test
    public void TC_05_Sucess() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSecond(3);

        // Dang ki 1 tai khoan truoc
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSecond(3);

        String firsName = "triet", lastName = "vu", emailAddress= getEmailAddress(), password ="123456789";
        String fullName = firsName + " " + lastName;

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firsName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();
        sleepInSecond(3);

        //verify register account
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName + "!");

               // locator khó
        String contactInfor  = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfor.contains(fullName));
        Assert.assertTrue(contactInfor.contains(emailAddress));

       // Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText());

     //   driver.findElement().getText().contains(); contains trả ve boolean

        //logout    // locator khó
        driver.findElement(By.cssSelector("a.skip-account")).click();
        sleepInSecond(3);
        driver.findElement(By.cssSelector("li.last>a[title='Log Out']")).click();
        sleepInSecond(3);

        //login
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSecond(3);


        //verify success login
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName + "!");

        contactInfor  = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfor.contains(fullName));
        Assert.assertTrue(contactInfor.contains(emailAddress));


        //verify account information
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSecond(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firsName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), emailAddress);

    }
    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String getEmailAddress() {
        Random rand = new Random();
        /*String emailAddress = "automation" + rand.nextInt(9999) + "@gmail.net";
        return emailAddress;*/
        return "automation" + rand.nextInt(9999) + "@gmail.net";
    }
}
