package unittests;

import database.DatabaseManager;
import database.EntityUser;
import database.Validator;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnitTest {
    private List<EntityUser> users = null;
    private int size = 0;
    private static int id;
    private Validator validator;
    private static DatabaseManager databaseManager;
    @BeforeClass
    public static void init2()
    {
        databaseManager = new DatabaseManager();
        databaseManager.addUser("testname3","2ffswe4@Eas","test@gmail.com","123456789");
        databaseManager.addUser("testname2","2ffswe4@Eas","test@gmail.com","123456789");
        id = databaseManager.getUserbyName("testname2").getId();
    }
    @Before
    public void init()
    {
        validator = new Validator();
        users = databaseManager.getUsers();
        size = users.size();
    }

    @Test
    public void testNameValidator()
    {
        boolean result = validator.ValidateName("nick");
        assertEquals("Correct login ",true,result);
        result = validator.ValidateName("1nick");
        assertEquals("Wrong login",false,result);

    }
    @Test
    public void testPasswordValidator()
    {
        boolean result = validator.ValidatePassword("andjJS&^681s");
        assertEquals("Correct password",true,result);
        result = validator.ValidatePassword("12345678");
        assertEquals("Wrong password",false,result);

    }
    @Test
    public void testPhoneNumberValidator()
    {
        boolean result = validator.ValidateNumber("123456789");
        assertEquals("Correct phone number",true,result);
        result = validator.ValidateNumber("12345678");
        assertEquals("Wrong phone number",false,result);

    }
    @Test
    public void testEmailValidator()
    {
        boolean result = validator.ValidateEmail("email@gmail.com");
        assertEquals("Correct email address",true,result);
        result = validator.ValidateEmail("email@gmail");
        assertEquals("Wrong email address",false,result);

    }
    @Test
    public void testgetUserbyName()
    {
        String name = "testname3";
        EntityUser user = databaseManager.getUserbyName(name);
        assertEquals("Name",name,user.getName());
    }
    @Test
    public void testaddUser()
    {
        databaseManager.addUser("testname","2ffswe4@Eas","test@gmail.com","123456789");
        assertEquals("Size after add",size+1,databaseManager.getUsers().size());
    }
    @Test
    public void testremoveUser()
    {
        databaseManager.removeUser(this.id);
        assertEquals("Size after remove",size-1,databaseManager.getUsers().size());
    }
    @AfterClass
    public static void end() {
        databaseManager.removeUser(databaseManager.getUserbyName("testname").getId());
        databaseManager.removeUser(databaseManager.getUserbyName("testname3").getId());
        databaseManager.close();
    }
}
