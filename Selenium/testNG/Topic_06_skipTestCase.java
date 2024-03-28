package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_skipTestCase {
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    @Test (enabled = false) //******
    public void Priority_03_TestSearchWithDate() {
        System.out.println("hello");

    }

    @Test
    public void Priority_01_TestSearchWithDateBilling() {
        System.out.println("hi");
    }

    // so luong test case nhieu ma danh so thi rat mat thoi gian
/*    @Test(priority = 1)
    public void testSearchWithDateBilling() {
        System.out.println("hi");
    }*/
    @Test
    public void Priority_02_TestSearchWithProduct() {
        System.out.println("by");

    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }
}
