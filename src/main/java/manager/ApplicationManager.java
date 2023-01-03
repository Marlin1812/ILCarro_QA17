package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    //    WebDriver wd;
    EventFiringWebDriver wd;
    HelperUser user;
    HelperCar car;

    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init(){
//        wd = new ChromeDriver();
        if(browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Tests on FIREFOX");
        } else if(browser.equals(BrowserType.CHROME)){
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Tests on CHROME");
        }
//        wd = new EventFiringWebDriver(new ChromeDriver());
//        wd.register(new MyListener());
//        wd.manage().window().maximize();
        wd.navigate().to("https://ilcarro.web.app/");
        user = new HelperUser(wd);
        car = new HelperCar(wd);

    }

    public HelperCar getCar() {
        return car;
    }

    public HelperUser getUser() {
        return user;
    }
}