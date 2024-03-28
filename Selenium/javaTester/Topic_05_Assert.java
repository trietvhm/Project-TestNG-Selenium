package javaTester;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;

    @Test
    public void verifyTestNG(){
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
        // trong java co nhieu thu vien de verify du lieu
        // nhung thu vien nay goi la testing framework (Unit/ Intergration/ UI Automation Test)
        // VD version noi tieng: Junit 4,5/ TestNG/ AssertJ/....
        // TestNG vừa là thư viện vừa là runner

        //Kieu du lieu nhan vao la boolean (true/false)
        // Khi mong muon dieu kien tra ve la dung thi assertTrue de verify
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));

        // Mong muon dieu kien tra ve la sai thi dung assertFalse
        Assert.assertFalse(driver.getPageSource().contains("Create a new account"));


        //driver.findElement(By.id("")).isDisplayed();
        //driver.manage().timeouts();
    }
}
