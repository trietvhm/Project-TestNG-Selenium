package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_popUp_02 {
    WebDriver driver;

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Random_popUp_NOT_IN_DOM_01() {
        driver.get("https://www.javacodegeeks.com/");
        sleepInSecond(10);

        By newsLetterPopUp = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");

        // Neu hien thi thì nhảy vào close no di
        // Luon chya duoc vi element luon co trong HTML/DOM

        if (driver.findElements(newsLetterPopUp).size() > 0 && driver.findElements(newsLetterPopUp).get(0).isDisplayed()) {
            // > 0 co nghia la no da duoc render ra roi, nhung chua biet la hien thi hay khong hien thi
            // Dung findelements so nhieu no se cho het timeout implicit ==> cho het 30s moi nhay qua step tiep theo

            System.out.println("Popup hiển thị: ");
            driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-bounceIn div.lepopup-element-html-content a"))
                    .click();
            sleepInSecond(2);
        } else {
            System.out.println("Popup khong hien thi");
        }

        // Nhap vao Field Search du lieu
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");

        // Click search
        driver.findElement(By.cssSelector("button#search-submit")).click();
        sleepInSecond(3);

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());


        // Neu khong hien thi thì qua step tiep theo (Search du lieu)


    }

    @Test
    public void TC_023_Random_popUp_IN_DOM_01() {
        driver.get("https://vnk.edu.vn/");
        //sleepInSecond(40);

        By newsLetterPopUp = By.cssSelector("div#tve-p-scroller article");

        // Neu hien thi thì nhảy vào close no di
        // Luon chya duoc vi element luon co trong HTML/DOM

        if (driver.findElement(newsLetterPopUp).isDisplayed()) {
            // isDisplayed la boi vi no luon luon co
            // render ra la co san luon
            System.out.println("Popup hiển thị: ");
            driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
            sleepInSecond(2);
        } else {
            System.out.println("Popup khong hien thi");
        }

        //   Click search
        //   driver.findElement(By.cssSelector("button.btn-danger")).click();
        driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
        sleepInSecond(10);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(), "Lịch Khai Giảng");


        // Neu khong hien thi thì qua step tiep theo (Search du lieu)

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
