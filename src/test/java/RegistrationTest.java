import manager.ProviderData;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }

    @Test(dataProvider = "registrationCSV", dataProviderClass = ProviderData.class)
    public void registrationPositiveTestCSV(User user){
//        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
//        User user = new User()
//                .withName("Joe")
//                .withLastName("Doe")
//                .withEmail("joe" + i + "@mail.com")
//                .withPassword("$Asdf1234");

        logger.info("registrationPositiveTest with email: " + user.getEmail()
                + " password: " + user.getPassword() + " name: " + user.getName() + "lastName: " + user.getLastName());

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().pause(3000);
        app.getUser().submitRegistration();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isRegistered());
    }
    @Test
    public void registrationPositiveTest(){
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withName("Joe")
                .withLastName("Doe")
                .withEmail("joe" + i + "@mail.com")
                .withPassword("$Asdf1234");

        logger.info("registrationPositiveTest with email: " + user.getEmail()
                + " password: " + user.getPassword());

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().pause(3000);
        app.getUser().submitRegistration();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isRegistered());
    }
    @Test
    public void registrationNegativeTestWithIncorrectPassword(){
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withName("Joe")
                .withLastName("Doe")
                .withEmail("joe" + i + "@mail.com")
                .withPassword("sdf1234");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().pause(3000);
//        app.getUser().submitRegistration();
//        app.getUser().pause(3000);
//        Assert.assertTrue(app.getUser().isRegistered());
    }

}
