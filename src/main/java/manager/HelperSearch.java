package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

public class HelperSearch extends HelperBase{

    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void fillSearchForm(String city, String dateFrom, String dateTo) {
        fillCity(city);
//        selectPeriod(dateFrom, dateTo);
//        selectPeriodYears(dateFrom, dateTo);
        selectPeriodMonths(dateFrom, dateTo);


    }

    private void selectPeriodMonths(String dateFrom, String dateTo) {

        int nowToStartMonth = 0;
        String[] startDate = dateFrom.split("/");
        String[] endDate = dateTo.split("/");
        pause(3000);
        click(By.id("dates"));
        if (LocalDate.now().getMonthValue()!=Integer.parseInt(startDate[0])){
            nowToStartMonth = Integer.parseInt(startDate[0]) - LocalDate.now().getMonthValue();

        }
        for(int i=0; i<nowToStartMonth; i++){
            click(By.xpath());
        }

    }

//    private void selectPeriodYears(String dateFrom, String dateTo) {
//
//
//
//    }

    private void selectPeriod(String dateFrom, String dateTo) {

        String[] startDate = dateFrom.split("/");
        String[] endDate = dateTo.split("/");
        pause(3000);
        click(By.id("dates"));
//        String locatorStart = "//div[.=' 6 ']";
//        String locatorStart = "//div[.=' "+startDate[1]+" ']";
        String locatorStart = String.format("//div[.=' %s ']", startDate[1]);
        String locatorEnd = String.format("//div[.=' %s ']", endDate[1]);
        click(By.xpath(locatorStart));
        click(By.xpath(locatorEnd));

    }

    private void fillCity(String city) {

        type(By.id("city"),city);
        pause(3000);
        click(By.cssSelector("div.pac-item"));
        pause(3000);

    }


    public void submitForm() {

        click(By.xpath("//button[@type='submit']"));

    }
}
