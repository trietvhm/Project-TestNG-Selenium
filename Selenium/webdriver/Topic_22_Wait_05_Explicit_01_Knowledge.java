package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_05_Explicit_01_Knowledge {
    WebDriver driver;

    WebDriverWait explicitWait; //Khai bao chua khoi tao

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();

        // khoi tao 1 explicitWait co tong thoi gian la 10s - Polling la 0.5s mặc định
        // 500ms = 0.5s
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Method() {
        // WAIT cho 1 Alert presence trong HTML/DOM truoc khi thao tac len
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());// tra ve elemtn kieu du lieu alert
        alert.accept();

        // WAIT cho 1-n  element đuuoc hien thi tren UI
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("")), driver.findElement(By.xpath(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(
                driver.findElement(By.xpath("")),
                driver.findElement(By.xpath("")),
                driver.findElement(By.xpath(""))));

        // WAIT cho 1 element bien mat (khong hien thi tren giao dien UI)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(""))));

        // WAIT cho element co trong DOM( khong quan tam co trên UI không)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        // WAIT cho 1 list element co trong DOM
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        //Wait cho element khong o trong DOM nua (truoc do xuat hien trong DOM ve sau no ko con nua)
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(""))));


        // WAIT cho 1 element duoc phep click()
        // khi page con dang load thi cac element chua ready de click
        // link/button/checkbox/radio
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        // Wait title truoc khi get phong trong truong hop page dang con
        // Wait title trong truong hop click vao 1 button, no van dang con o page truoc do chua load xong thi no se lay cai title ko dung ma minh can
        // Wait cho page hien tai co title nhu mong doi
        explicitWait.until(ExpectedConditions.titleIs(""));
        driver.getTitle();

        // And ket hop nhieu dieu kien
        // And cả 2 thg trong ngoac deu phai dung thi no moi chay dung
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        //OR 1 trong 2 thg đúng đều đuoc
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        // WAIT cho 1 element có attribute chứa giá trị mong đợi
        // Tương đối
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"),
                "placeholder",
                "Search entire "));
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"),
                "placeholder",
                "store here..."));
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"),
                "placeholder",
                "Search entire store here..."));

        // WAIT cho 1 element có attribute chứa giá trị mong đợi, phai đay đủ hết, chu chứa là nó không đúng
        // Tuyệt đối
        explicitWait.until(ExpectedConditions.attributeToBe(
                By.cssSelector("input#search"),
                "placeholder",
                "Search entire store here..."));

        // Wait cho 1 element có gi trị khác NULL
        //Mong đợi 1 attribute từ không có du liệu thành có du liệu
        // VD: đăng ki tài khoản
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(
                driver.findElement(By.cssSelector("input#search")),
                "placeholder"));

        // WAIT cho 1 element duoc selected thanh cong
        // Checkbox/ RADIO/ DROPDOWN item (default)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        // WAIT cho 1 element được SELECTED rồi
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),true));

        // WAIT cho 1 element chưa được SELECTED
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),false));

        // WAIt cho 1 frame/iframe duoc abailale và SWITCH qua
        // Name or ID
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(""));

        // index---> int
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));

        //By or Element
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));

        // WAIT cho so luong element bang 1 con so co dinh
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("select[title='Sort By']>option "),10));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("select[title='Sort By']>option "),10));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("select[title='Sort By']>option "),10));

        // WAIT cho bao nhieu tab/ Window
        // Chi WAIT cho cua so, ko WAIT cho page duoc load xong
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

        // Wait cho 1 dieu kien ma element nay no update trang thai lai - load lai HTML
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.cssSelector(""))));


    }


    @Test
    public void TC_02_() {

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
