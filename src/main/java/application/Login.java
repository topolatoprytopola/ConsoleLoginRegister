package application;

import database.DatabaseManager;
import database.Validator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    private DatabaseManager databaseManager;
    private  Scanner scanner;
    private Validator validator;
    private String login;
    public Login()
    {
        databaseManager = new DatabaseManager();
        scanner = new Scanner(System.in);
        validator = new Validator();
    }
    public void SignIn()
    {
        String login="";
        String password="";
        while(true)
        {
            System.out.println("Your login");
            login = scanner.next();
            System.out.println("Your password");
            password = scanner.next();
            if(this.ValidateCorrectnessofLogin(password))
            {
                this.login = login;
                this.ManageAccount();
                break;
            }
            else
            {
                System.out.println("Wrong login or password");
            }
        }
        System.out.println("You have logged out");
        databaseManager.close();
    }
    private boolean ValidateCorrectnessofLogin(String password)
    {
        if(databaseManager.getUserbyName(login) != null)
        {
            if(databaseManager.getUserbyName(login).getPassword().equals(password))
            {
                return true;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return true;
        }
    }
    private void ManageAccount()
    {
        loop:while(true)
        {
            System.out.println("You are logged as "+login);
            System.out.println("Your email: "+databaseManager.getUserbyName(login).getEmail());
            System.out.println("Your phone number: "+databaseManager.getUserbyName(login).getPhonenumber());
            System.out.println("What do you want to do?");
            System.out.println("1 - Change email address");
            System.out.println("2 - Change phone number");
            System.out.println("3 - Exit");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        this.ChageEmail();
                        break;
                    case 2:
                        this.ChangePhoneNumber();
                        break;
                    case 3:
                        break loop;
                    default:
                        System.out.println("Invalid input!");
                        break;
                }
            } catch (InputMismatchException e)
            {
                System.out.println("Invalid input!");
                scanner.next();
            }
        }
    }
    private void ChageEmail()
    {
        String email = "";
        while (true)
        {
            System.out.println("New email");
            email = scanner.next();
            if(validator.ValidateEmail(email))
            {
                databaseManager.editEmail(databaseManager.getUserbyName(login),email);
                break;
            }
            else
            {
                System.out.println("Wrong email");
            }
        }

    }
    private void ChangePhoneNumber()
    {
        String phoneNumber = "";
        while (true)
        {
            System.out.println("New phone number");
            phoneNumber = scanner.next();
            if(validator.ValidateNumber(phoneNumber))
            {
                databaseManager.editPhoneNumber(databaseManager.getUserbyName(login),phoneNumber);
                break;
            }
            else
            {
                System.out.println("Wrong phone number");
            }
        }
    }
}
