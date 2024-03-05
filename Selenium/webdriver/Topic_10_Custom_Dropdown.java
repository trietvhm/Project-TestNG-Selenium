package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;

    // WAIT tường minh: trang thai cụ thểcho elelment
    // chờ cho element thỏa mãn 1 dk nào đó
    // Visible/ Invisible/ presence (co o trong HTML nhung khong hien thi)/
    // number of element/ Clickable
    WebDriverWait explicitWait; // Wait tuong minh

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass.
    // Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // WAIT ngam dinh: ko ro rang cho 1 trang thai cu the nao cua element het
        // ngam dinh cho viec tim element - findelement(s)
        // chờ cho element duoc tim thay
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

//        B1: click vào 1 thẻ để cho nó xổ hết các item bên trong ra
        driver.findElement(By.cssSelector("span#number-button")).click();
        sleepInSecond(10);


//        B2: Có 2 trường hợp
//        TH1: nó sẽ xổ ra và chứa hết tất cả các item
//        Th2: nó sẽ xổ ra nhưng chỉ chứa 1 phần nhưng đang load thêm vì có dropdown có vài ngàn items ===> phải có cơ chế wait để cho nó load hết
        // Ngàn/triệu record
        // ====> chờ cho nó xồ ra hết tất cả các item ở trong dropdown
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));

        // allitems chua 19 items bên trong
        // list dđang chứa 19 item có kieu du lieu la webElement
        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));

        // for each
        // 1 biến đai dien cho kieu element, duyet qua từng thằng trong tất ca items có mặt trong list tren
        for (WebElement items : allItems) {
            // Nếu trường hợp element click chon xong roi cac item con lai se khong con trong HTML nữa
            // thì hàm getText se bi fail
            String textItems = items.getText();
            System.out.println("Text item: "+ textItems);

            // String
            if (textItems.equals("8")){
                items.click();
                break; // 9->19 không được kiểm tra
            }
        }

//        B3:
//        Th1: Nếu như item cần chọn nó hiển thị thì click vào
//        TH2: Nếu như item cần chọn nằm bên dưới, thì 1 số trường hợp cần scroll xuống để hiển thị lên rồi mới chọn (Vuejs, Angular, React,.....)

//        B4:
//        Trước khi click cần kiểm tra nếu như text của item bằng với item cần chọn thì click vào


    }

    @Test
    public void TC_02_() {

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
