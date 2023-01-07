import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends TestBase{

    @Test

    public void searchTest(){

        app.getSearch().fillSearchForm("Tel Aviv", "1/6/2023", "1/15/2023");
        app.getSearch().submitForm();
        Assert.assertTrue(app.getSearch().isElementPresent(By.className("search-results")));

    }

}
