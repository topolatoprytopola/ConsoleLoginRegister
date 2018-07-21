package database;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public boolean ValidateName(String name)
    {
        DatabaseManager databaseManager = new DatabaseManager();
        Pattern p = Pattern.compile("[a-zA-Z][a-zA-Z_0-9][a-zA-Z_0-9]+");
        Matcher m = p.matcher(name);
        if(m.matches())
        {
            List<EntityUser> users = databaseManager.getUsers();
            for(EntityUser entityUser:users)
            {
                if(entityUser.getName().equals(name))
                {
                    databaseManager.close();
                    return false;
                }
            }
            databaseManager.close();
            return true;
        }
        else {
            databaseManager.close();
            return false;
        }
    }
    public boolean ValidatePassword(String password)
    {
        Pattern p = Pattern.compile(".{8,}");
        Matcher m = p.matcher(password);
        if(m.matches())
        {
            if(password.matches(".*\\d+.*")) {
                if (password.matches(".*[A-Z]+.*"))
                {
                    if (password.matches(".*[^A-Za-z0-9]+.*")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean ValidateEmail(String email)
    {
        Pattern p = Pattern.compile("[a-zA-Z][a-zA-Z_0-9]*[@][a-zA-Z_0-9]+[.][a-zA-Z_0-9]+");
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public boolean ValidateNumber(String number)
    {
        Pattern p = Pattern.compile("\\d{9}");
        Matcher m = p.matcher(number);
        return m.matches();
    }
}
