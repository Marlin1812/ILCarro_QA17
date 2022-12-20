import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationNegativeTest extends TestBase{

//    QA17_HW_11_Stas
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
