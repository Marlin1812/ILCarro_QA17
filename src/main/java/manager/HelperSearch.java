package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase{

    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void fillSearchForm(String city, String dateFrom, String dateTo) {
        fillCity(city);
//        selectPeriod(dateFrom, dateTo);
//        selectPeriodYears(dateFrom, dateTo);
//        selectPeriodMonths(dateFrom, dateTo);
        selectPeriodYears(dateFrom, dateTo);
    }

    private void selectPeriodMonths(String dateFrom, String dateTo) {

        int nowToStartMonth = 0, startToEndMonth = 0;
        String[] startDate = dateFrom.split("/");
        String[] endDate = dateTo.split("/");
        startToEndMonth = Integer.parseInt(endDate[0]) - Integer.parseInt(startDate[0]);
        pause(3000);
        click(By.id("dates"));
        if(LocalDate.now().getMonthValue()!=Integer.parseInt(startDate[0])){
            nowToStartMonth = Integer.parseInt(startDate[0]) - LocalDate.now().getMonthValue();
        }
        for(int i = 0; i < nowToStartMonth; i++){
            click(By.xpath(("//button[@aria-label='Next month']")));
        }

        String locatorStart = String.format("//div[.=' %s ']", startDate[1]);
        String locatorEnd = String.format("//div[.=' %s ']", endDate[1]);
        click(By.xpath(locatorStart));

        for(int i = 0; i < startToEndMonth; i++){
            click(By.xpath(("//button[@aria-label='Next month']")));
        }

        click(By.xpath(locatorEnd));

    }


    private void selectPeriodYears(String dateFrom, String dateTo) {

    LocalDate startDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    LocalDate endDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    LocalDate nowDate = LocalDate.now();
        String locatorStart = String.format("//div[.=' %s ']", startDate.getDayOfMonth());
        String locatorEnd = String.format("//div[.=' %s ']", endDate.getDayOfMonth());

        click(By.id("dates"));
        //                    01.01.2024            10.01.2023
        int startToEndMonth = startDate.getYear() - nowDate.getYear() == 0 ?
                startDate.getMonthValue() - nowDate.getMonthValue() :
                12 - nowDate.getMonthValue() + startDate.getMonthValue()
                ;
        for(int i = 0; i < startToEndMonth; i++){
            click(By.xpath(("//button[@aria-label='Next month']")));
        }
        click(By.xpath(locatorStart));

        startToEndMonth = endDate.getYear() - startDate.getYear() == 0 ?
                endDate.getMonthValue() - startDate.getMonthValue() :
                12 - startDate.getMonthValue() + endDate.getMonthValue()
        ;
        for(int i = 0; i < startToEndMonth; i++){
            click(By.xpath(("//button[@aria-label='Next month']")));
        }
        click(By.xpath(locatorEnd));

    }


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
