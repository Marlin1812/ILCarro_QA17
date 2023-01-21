package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    String browser;

    //    WebDriver wd;
    EventFiringWebDriver wd;
    HelperUser user;
    HelperCar car;
    HelperSearch search;
    Properties properties;

//    public ApplicationManager() {
//        properties = new Properties();
//    }

    public ApplicationManager(String browser) {
        properties = new Properties();
        this.browser = browser;
    }
    public void init() throws IOException {
//        wd = new ChromeDriver();
        String target = System.getProperty("target","config");
//        properties.load(new FileReader(new File("src/test/resources/config.properties")));
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if(browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Tests on Chrome started");
        } else if(browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Tests on FireFox started");
        }
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.register(new MyListener());
        wd.manage().window().maximize();
//        wd.navigate().to("https://ilcarro.web.app/");
        wd.navigate().to(properties.getProperty("web.baseURL"));
        user = new HelperUser(wd);
        car = new HelperCar(wd);
        search = new HelperSearch(wd);
    }

    public HelperSearch getSearch() {
        return search;
    }

    public HelperCar getCar() {
        return car;
    }

    public HelperUser getUser() {
        return user;
    }

    public String getEmail() {
        return properties.getProperty("web.email");
    }
    public String getPassword() {
        return properties.getProperty("web.password");
    }
}