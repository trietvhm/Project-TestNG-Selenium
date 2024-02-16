package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.SortedMap;

public class Topic_12_Driver_Infor {
    WebDriver driver;
    @Test
    public void testDriverInformation(){
        driver = new FirefoxDriver();
        //  Ở trên OS nao
        // Browser nào
        // Id cua driver la gi
        // FirefoxDriver: firefox on windows (18f57cfe-8999-4f42-99ea-1e007333cc91)

        System.out.println(driver.toString());
        driver.quit();

    }
}
