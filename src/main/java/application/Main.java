package application;


import unittests.UnitTests;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String [] args)
    {
        Scanner scanner = new Scanner(System.in);
        loop:while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1 - Sign In");
            System.out.println("2 - Register");
            System.out.println("3 - Test app");
            System.out.println("4 - Exit");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        Login login = new Login();
                        login.SignIn();
                        break;
                    case 2:
                        Register register = new Register();
                        register.CreateAccount();
                        break;
                    case 3:
                        new UnitTests().StartUnitTests();
                        break;
                    case 4:
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
        scanner.close();
    }
}
