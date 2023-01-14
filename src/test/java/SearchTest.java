import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends TestBase{

    @Test
    public void searchTest(){

        app.getSearch().fillSearchForm("Tel Aviv","01/01/2024","01/10/2024");
        app.getSearch().submitForm();
        Assert.assertTrue(app.getSearch().isElementPresent(By.className("search-results")));
    }

}
