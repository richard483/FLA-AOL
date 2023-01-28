package facade;

import java.util.Scanner;

import models.Member;
import models.ProMember;
import models.User;
import proxy.AuthProxy;
import repository.DBConnection;

public class MenuFacade {
    Scanner scan = new Scanner(System.in);
    DBConnection dbConnection;
    AuthProxy authProxy;

    public MenuFacade() {
        dbConnection = new DBConnection();
        authProxy = new AuthProxy(dbConnection);
    }

    public void showWelcomeMenu() {
        int input = 0;

        System.out.println("Welcome to JAVA Link Shortener!");

        do {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Type your input >> ");
            input = scan.nextInt();
            scan.nextLine();

            switch (input) {
                case 1:
                    System.out.println();
                    System.out.println();
                    showLoginMenu();
                    break;
                case 2:
                    showRegisterMenu();
                    break;
                case 0:
                    System.out.println("Thank you for using our service!");
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while (input != 0);

    }

    private void showLoginMenu() {
        String username;
        String password;

        System.out.println("LOGIN");
        System.out.println("==============");
        System.out.println("Please fill up your data");
        do {
            System.out.println("Username : ");
            username = scan.nextLine();
        } while (!(username.length() > 5) || !(username.length() < 50));
        do {
            System.out.println("Password : ");
            password = scan.nextLine();
        } while (!(password.length() > 6));

        User authUser = authProxy.login(username, password);

        if (authUser == null) {
            System.out.println("Login failed");
            System.out.println("Press enter to continue ...");
            return;
        } else {
            System.out.println("Welcome " + authUser.getUsername());
            // TODO: show shortlink menu
        }

        System.out.println("Login succesfull");
        System.out.println("Press enter to continue ...");
    }

    private void showRegisterMenu() {
        String username = "";
        String password = "";
        String role = "";

        System.out.println("REGISTER");
        System.out.println("==============");
        System.out.println("Register Your Data");
        System.out.println();

        do {
            System.out.print("Please Register Your Username [5 - 50 Characters]: ");
            username = scan.nextLine();
        } while (username.length() < 5 || username.length() > 50);

        do {
            System.out.print("Please Register Your Password [Min 6 Characters]: ");
            password = scan.nextLine();
        } while (password.length() < 6);

        do {
            System.out.print("Please Choose Account Role [Member | Pro-Member](inclusive): ");
            role = scan.nextLine();
        } while (!role.equals("Member") && !role.equals("Pro-Member"));

        User registeredUser;

        if (role == "Member") {
            registeredUser = new Member(username, password, "member");
        } else {
            registeredUser = new ProMember(username, password, "pro-member");
        }

        if (!authProxy.register(registeredUser)) {
            System.out.println("Register Failed");
            System.out.println("Press enter to continue ...");
            return;
        }

        System.out.println("Your Account has been Registered!");
        System.out.println("Press Enter to Continue...");
        scan.nextLine();
        System.out.println("\n\n");
    }

    public void shortLinkMenu() {
        int input = 0;

        System.out.println("Welcome to JAVA Link Shortener!");
        do {
            System.out.println("1. Create Short Link");
            System.out.println("2. View Short Link");
            System.out.println("3. Delete Short Link");
            System.out.println("0. Exit");
            System.out.println("Type your input >> ");
            input = scan.nextInt();
            scan.nextLine();

            switch (input) {
                case 1:
                    System.out.println();
                    System.out.println();
                    showCreateShortLinkMenu();
                    break;
                case 2:
                    showViewShortLinkMenu();
                    break;
                case 3:
                    showDeleteShortLinkMenu();
                    break;
                case 0:
                    System.out.println("Thank you for using our service!");
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while (input != 0);
    }

    private void showDeleteShortLinkMenu() {
    }

    private void showViewShortLinkMenu() {
    }

    private void showCreateShortLinkMenu() {
    }

}
