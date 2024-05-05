package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_21_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String hcmName = "saigon.jpg";
    String dnName = "danang.jpg";
    String hnName = "hanoi.jpg";

    String hcmFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hcmName; // File.separator: Biến này tự động chèn ký tự phân cách đường dẫn thích hợp dựa trên hệ điều hành.
    String dnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + dnName;
    String hnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hnName;


    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        //String filePath = "D:\\Automation_testing\\02_ Selenium Webdriver\\New-project-TestNG-NEW\\uploadFiles\\danang.jpg";

        // File nay nam o dau?
        // Trong thu muc uploadFile

        // Neu may khac dung OS khac thi co chay duoc khong?
        // D:\Automation_testing\02_ Selenium Webdriver\New-project-TestNG-NEW\\uploadFiles\danang.jpg
        // Neu dung duong dan nay thi chi co may nay chay duoc thoi, minh dang fix cứng

        // Đe cho máy khác xài duoc thì ta phai lay duong dan tương đối của file dđó

        By uploadBy = By.xpath("//input[@type='file']");

        driver.findElement(uploadBy).sendKeys(hcmFilePath);
        sleepInSecond(2);

        driver.findElement(uploadBy).sendKeys(dnFilePath);
        sleepInSecond(2);

        driver.findElement(uploadBy).sendKeys(hnFilePath);
        sleepInSecond(2);

        // verify file đươc load len thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hcmName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dnName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hnName + "']")).isDisplayed());

        List<WebElement> startButton = driver.findElements(By.cssSelector("td>button.start"));

        // C1
        // clasic for
/*        for (int i = 0; i <startButton.size();i++){
            if (startButton.get(i).isDisplayed()){
                startButton.get(i).click();
                sleepInSecond(2);
            }
        }*/

        // C2
/*        for (WebElement button: startButton){
            button.click();
            sleepInSecond(2);
        }
        */

        driver.findElement(
                By.xpath("//p[text()='saigon.jpg']/parent::td/following-sibling::td//child::button[contains(@class,'start')]")).click();

        driver.findElement(By.xpath(
                "//p[text()='danang.jpg']/parent::td/following-sibling::td//child::button[contains(@class,'start')]")).click();

        driver.findElement(By.xpath(
                "//p[text()='hanoi.jpg']/parent::td/following-sibling::td//child::button[contains(@class,'start')]")).click();

        // verify file đươc upload len thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hcmName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + dnName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hnName + "']")).isDisplayed());

        System.out.println(hcmFilePath);
        System.out.println(hnFilePath);
        System.out.println(dnFilePath);
        System.out.println(projectPath);
    }

    @Test
    public void TC_02_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        //String filePath = "D:\\Automation_testing\\02_ Selenium Webdriver\\New-project-TestNG-NEW\\uploadFiles\\danang.jpg";

        // File nay nam o dau?
        // Trong thu muc uploadFile

        // Neu may khac dung OS khac thi co chay duoc khong?
        // D:\Automation_testing\02_ Selenium Webdriver\New-project-TestNG-NEW\\uploadFiles\danang.jpg
        // Neu dung duong dan nay thi chi co may nay chay duoc thoi, minh dang fix cứng

        // Đe cho máy khác xài duoc thì ta phai lay duong dan tương đối của file dđó

        By uploadBy = By.xpath("//input[@type='file']");

        // Set duong dan qua nhieu thi nó ko load file len duoc 141
        // Tuy nhien 2 file tro len goi la multiple nen load it cug dc tinh
        driver.findElement(uploadBy).sendKeys(hcmFilePath + "\n" + dnFilePath + "\n" + hnFilePath);
        sleepInSecond(2);

        // verify file đươc load len thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hcmName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dnName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hnName + "']")).isDisplayed());

        List<WebElement> startButton = driver.findElements(By.cssSelector("td>button.start"));

        // C1
        // clasic for
/*        for (int i = 0; i <startButton.size();i++){
            if (startButton.get(i).isDisplayed()){
                startButton.get(i).click();
                sleepInSecond(2);
            }
        }*/

        // C2
/*        for (WebElement button: startButton){
            button.click();
            sleepInSecond(2);
        }
        */

        driver.findElement(
                By.xpath("//p[text()='saigon.jpg']/parent::td/following-sibling::td//child::button[contains(@class,'start')]")).click();

        driver.findElement(By.xpath(
                "//p[text()='danang.jpg']/parent::td/following-sibling::td//child::button[contains(@class,'start')]")).click();

        driver.findElement(By.xpath(
                "//p[text()='hanoi.jpg']/parent::td/following-sibling::td//child::button[contains(@class,'start')]")).click();

        // verify file đươc upload len thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hcmName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + dnName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hnName + "']")).isDisplayed());

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

}
