package manager;

import org.openqa.selenium.*;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size() > 0;
    }

    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator){
        wd.findElement(locator).click();
    }

    public void pause(int time){
//        wd.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
//        time = time * 1000;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String getText(By locator){
        return wd.findElement(locator).getText();
    }

//    public void takeScreenShot(String Link){
//
//        File tmp = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
//        File screenshot = new File(Link);
//
//        Files.copy(tmp, screenshot);
//    }
}
