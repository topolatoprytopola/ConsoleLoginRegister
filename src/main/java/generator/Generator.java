package generator;

import database.Validator;

import java.util.Random;

public class Generator {
    public String generatePassword()
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*_=+-/";
        int alphabetlength = alphabet.length();
        Validator validator = new Validator();
        String password = "";
        while(true) {
            password = "";
            Random rand = new Random();
            int number = rand.nextInt(20) + 8;
            for (int i = 1; i <= number; i++) {
                //TODO
                int x = rand.nextInt(alphabetlength);
                password += alphabet.charAt(x) ;
            }
            if(validator.ValidatePassword(password))
            {
                break;
            }
        }
        return password;
    }
}
