package application;

import database.DatabaseManager;
import database.Validator;
import generator.Generator;

import java.util.Scanner;

public class Register {
    private DatabaseManager databaseManager;
    private Scanner scanner;
    private Validator validator;
    public Register()
    {
        databaseManager = new DatabaseManager();
        scanner = new Scanner(System.in);
        validator = new Validator();
    }
    public void CreateAccount()
    {
        String login="";
        String password="";
        String email="";
        String phonenumber="";
        while (true)
        {
            System.out.println("Your login");
            login = scanner.next();
            System.out.println("Your password or enter g to generate password");
            password = scanner.next();
            if (password.equals("g"))
            {
                password = new Generator().generatePassword();
                System.out.println("Your generated password: "+password);
            }
            System.out.println("Your email");
            email = scanner.next();
            System.out.println("Your phone number");
            phonenumber = scanner.next();
            if(validator.ValidateName(login) & validator.ValidatePassword(password) & validator.ValidateEmail(email) & validator.ValidateNumber(phonenumber))
            {
                databaseManager.addUser(login,password,email,phonenumber);
                break;
            }
            else
            {
                System.out.println("Wrong data");
            }
        }
        System.out.println("Created new account");
        databaseManager.close();
    }
}
