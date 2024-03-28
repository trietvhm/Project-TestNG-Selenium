package testNG;

import org.testng.annotations.*;

public class Topic_02_Annotation {
    @BeforeClass
    public void beforeClass (){
        System.out.println("Before Class");
    }

    @AfterClass
    public void afterClass (){
        System.out.println("After Class");
    }
    @BeforeGroups
    public void beforeGroup (){
        System.out.println("Before Group");
    }

    @AfterGroups
    public void afterGroup(){
        System.out.println("After Group");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before Method");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("After Method");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After Test");
    }

    @Test
    public void test01(){
        System.out.println("Test 01");
    }
    @Test
    public void test02(){
        System.out.println("Test 02");
    }
    @Test
    public void test03(){
        System.out.println("Test 03");
    }
}
