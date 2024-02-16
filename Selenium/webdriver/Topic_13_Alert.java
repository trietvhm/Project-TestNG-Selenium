package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    By resultText = By.cssSelector("p#result");

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSecond(3);

        // Chờ cho alert present
        // Neu trong thoi gian cho ma Alert tu xuat hien thì tự switch vào
        // Neu het thoi gian ma chua xuat hien thi moi fail
        // Vua wait, vua switch
        // trong truong hop click xong ma thay lien thi switch qua luon khoi can wait
        // Wait se dsam bao alert se xuat hien truoc khi no thao tac
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        // Alert alert =  driver.switchTo().alert(); // ko tìm thay alert la fail ngay nen dung wait
        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        //Khi minh accept hoac cancel roi alert se mat luon ,
        // dung ham nay goi tiep action khac se khong chay, se bi loi
        alert.accept();

        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");
        //cancel Alert
       /* void dismiss();

        void accept(); // dung de accept alert, ko dùng cho authentication

        String getText();

        void sendKeys(String keysToSend);*/

    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        sleepInSecond(3);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        // verify
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.dismiss();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");

    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSecond(3);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        // verify
        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String text = "Selenium Webdriver";

        alert.sendKeys(text);
        sleepInSecond(3);

        alert.accept();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + text);
    }

    @Test
    public void TC_04_Authentication_byPass_To_Url() {
        String username = "admin";
        String password = "admin";
        // Thu vien Alert ko su dung cho Authentication Alert được
        // vi tinh bao mat
        // thong qua chorm devtool protocol (CDP) --> chorm/edge

        //  cach 1: nhap tthang83username/password vào đường link
        /*  driver.get("https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth");
          Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")).getText(),
                "Congratulations! You must have the proper credentials.");

        Assert.assertTrue(driver.findElement(
                By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());*/

        // cach 2: tu page A thao tac len 1 element no se qua page B (can phai thao tac voi Authen)
        driver.get("http://the-internet.herokuapp.com/");
        String authenLinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        /*String[] authenArray = authenLinkUrl.split("//");
        System.out.println(authenArray[0]);
        System.out.println(authenArray[1]);
        driver.get(authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1]);*/
        driver.get(getAuthenAlertByUrl(authenLinkUrl, username, password));

        Assert.assertTrue(driver.findElement(
                By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public void TC_05_Authentication_selenium_4x() {

    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public String getAuthenAlertByUrl(String url, String username, String password) {
        String[] authenArray = url.split("//");
        return authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1];
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
