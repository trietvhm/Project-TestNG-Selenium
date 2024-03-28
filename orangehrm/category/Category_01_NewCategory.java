package category;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Category_01_NewCategory {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.out.println("Before Class");
    }
    @Test(groups = "category")
    public void testCreateNewCategoryEmpty() {

    }
    @Test(groups = "category")
    public void testCreateNewCategoryNameAndDiscription() {

    }
    @Test(groups = "category")
    public void testCreateNewCategoryWithParentCategory() {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("After Class");
    }

}
