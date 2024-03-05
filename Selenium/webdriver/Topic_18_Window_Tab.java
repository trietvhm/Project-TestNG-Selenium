package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_18_Window_Tab {
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
    public void TC_01_Basic_form() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Lấy ra ID của tab hiện tại
        String basicFormID = driver.getWindowHandle();

        driver.findElement(By.xpath("// a[text()='GOOGLE']")).click();
        sleepInSecond(3);
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        // Lấy ra tất cả ID //Set khong dc trung lap
        /*Set<String> allId = driver.getWindowHandles();*/

        // Su dung vong lap duyet qua từng ID
        /*for (String ID : allId){
            // Neu nhu 1 ID nao ma khac voi parent ID thi Switch vao
            if (!ID.equals(basicFormID)){
                driver.switchTo().window(ID);
                // thoat khoi vong lap khong can kiem tra gia tri con lai neu co
                break;
            }
        }*/

        switchToWindowByID(basicFormID); // sau cau lenh nay da qua trang Google
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
        sleepInSecond(3);

        //Switch de quay lai trang basic form
        String googleTabID = driver.getWindowHandle(); // minh dung o dau minh get thi nó se ra ID tai vi tri do
        switchToWindowByID(googleTabID);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(3);

        switchToWindowByTitle("Facebook – log in or sign up"); //switch de thao tac voi man hinh facebook
        driver.findElement(By.cssSelector("input#email")).sendKeys("meat100kg@gmail.com");

        switchToWindowByTitle("Selenium WebDriver");
        sleepInSecond(3);
    }

    @Test
    public void TC_02_Kyna() {
        driver.get("https://skills.kynaenglish.vn/");
        String parentID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("div.hotline img[alt='facebook']")).click();
        sleepInSecond(3);

        switchToWindowByTitle("Kyna.vn | Ho Chi Minh City | Facebook" );

        driver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("meat100kg@gmail.com");
        sleepInSecond(3);

        switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia" );
        sleepInSecond(3);

        driver.findElement(By.cssSelector("div.hotline img[alt='youtube']")).click();
        sleepInSecond(3);

        switchToWindowByTitle("Kyna.vn - YouTube" );
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input#search")).sendKeys("AutomationFC");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#inner-header-container yt-formatted-string#text")).getText(),
                "Kyna.vn");

        closeAllWithoutParent(parentID);
    }

    @Test
    public void TC_03_Techpanda() {
        driver.get("https://live.techpanda.org/");
        String parentID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product Samsung Galaxy has been added to comparison list.");
        sleepInSecond(3);

        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product Sony Xperia has been added to comparison list.");
        sleepInSecond(3);

        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product IPhone has been added to comparison list.");
        sleepInSecond(3);

        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        sleepInSecond(3);

        switchToWindowByTitle("Products Comparison List - Magento Commerce" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"COMPARE PRODUCTS");

        switchToWindowByTitle("Mobile" );
        driver.findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
        closeAllWithoutParent(parentID);

    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

    public void switchToWindowByID(String expecetedID) { // neu nhu co nhieu hon 3 tab hoac 3 window thi ham nay hoat dong sai
        Set<String> allIDs = driver.getWindowHandles();
        //Dung vong lap duyet qua tung ID trong set ở trên

        for (String ID : allIDs) {
            // Neu nhu 1 ID nao ma khac voi parent ID thi Switch vao
            if (!ID.equals(expecetedID)) {
                driver.switchTo().window(ID);
                // thoat khoi vong lap khong can kiemm tra gia tri con lai neu co
                break;
            }
        }
    }

    public void closeAllWithoutParent (String parentID){
        Set<String> allIDs = driver.getWindowHandles();

        for (String ID : allIDs) {
            if (!ID.equals(parentID)) {
                driver.switchTo().window(ID); // khong bằng với parrentID th mình Switch vào

                // neu khong switch vao thi co close duoc ko?
                driver.close();
                // Close thì nó chi đóng 1 tab thôi và đóng tab nó đang đứng
                // Quit la dong het tat cả các, browser liên quan đến cái driver nó khởi tạo nên
                // Chay het vong lap naày còn moi parent
            }
        }
        driver.switchTo().window(parentID); // khi minh dong roi thi driver van con o cac tab vừa close, nen chuyen driver ve tab chính
    }

    public void switchToWindowByTitle(String expectedTitle) {
        // Lay het tat ca cac ID cua cac Window/Tab
        Set<String> allIDs = driver.getWindowHandles();

        //Dung vong lap duyet qua Set ID o tren
        for (String id : allIDs) {
            driver.switchTo().window(id); //Cho Switch vao tung ID truoc
            sleepInSecond(2);

            String actualTitle = driver.getTitle();  //Lay ra title cua tab/window hien tai
            if (actualTitle.equals(expectedTitle))
                break;
        }
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
