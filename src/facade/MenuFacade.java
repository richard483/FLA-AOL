package facade;

import java.util.ArrayList;
import java.util.Scanner;

import builder.UserBuilder;
import models.ShortLink;
import models.User;
import proxy.AuthProxy;
import proxy.LinkShortenerProxy;
import repository.DBConnection;

public class MenuFacade {
    Scanner scan = new Scanner(System.in);
    DBConnection dbConnection;
    AuthProxy authProxy;
    LinkShortenerProxy linkShortProxy;
    UserBuilder userBuilder;

    public MenuFacade() {
        dbConnection = new DBConnection();
        authProxy = new AuthProxy(dbConnection);
        userBuilder = new UserBuilder();

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
        } while (username.length() < 5 || username.length() > 50);
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
            System.out.println("Login succesfull");
            System.out.println("Welcome " + authUser.getUsername());
            System.out.println("Press enter to continue ...");
            System.out.println();

            linkShortProxy = new LinkShortenerProxy(dbConnection, authUser);

            shortLinkMenu();
        }
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
        userBuilder.setUsername(username);
        // melakukan build tiap komponen

        do {
            System.out.print("Please Register Your Password [Min 6 Characters]: ");
            password = scan.nextLine();
        } while (password.length() < 6);
        userBuilder.setPassword(password);

        do {
            System.out.print("Please Choose Account Role [Member | Pro-Member | Admin](inclusive): ");
            role = scan.nextLine();
        } while (!role.equals("Member") && !role.equals("Pro-Member") && !role.equals("Admin"));
        userBuilder.setRole(role);

        User registeredUser = userBuilder.build();

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
        String link = "";
        ArrayList<ShortLink> shortLink = linkShortProxy.getAllLinks();

        System.out.println("DELETE");
        System.out.println("==============");

        System.out.println();
        int num = 1;
        System.out.println("No | Id | Name | Link name | Original link ");
        for (ShortLink sl : shortLink) {
            System.out.println(num + " | " + sl.getId() + " | " + sl.getLink_name() + " | " + sl.getLong_link());
            num++;
        }
        System.out.println();

        System.out.print("Select which ShortLink to delete (inclusive): ");
        link = scan.nextLine();

        if (linkShortProxy.deleteShortUrl(link)) {
            System.out.println("Link has been deleted!");
        } else {
            System.out.println("Link not found!");
        }
    }

    private void showViewShortLinkMenu() {
        ArrayList<ShortLink> shortLink = linkShortProxy.getAllLinks();

        int num = 1;
        System.out.println("VIEW");
        System.out.println("==============");
        System.out.println();
        System.out.println("No | Id | Name | Link name | Original link ");
        for (ShortLink sl : shortLink) {
            System.out.println(num + " | " + sl.getId() + " | " + sl.getLink_name() + " | " + sl.getLong_link());
            num++;
        }
        System.out.println();

        System.out.println("Press Enter to Continue...");
        scan.nextLine();
    }

    private void showCreateShortLinkMenu() {
        String ogLink = "";
        String name = "";

        System.out.println("CREATE");
        System.out.println("==============");
        do {
            System.out.print("Original Link [Starts with 'https://']: ");
            ogLink = scan.nextLine();
        } while (!ogLink.startsWith("https://"));
        do {
            System.out.print("ShortLink Name [No spaces]: ");
            name = scan.nextLine();
        } while (name.contains(" "));

        if (linkShortProxy.createShortUrl(ogLink, name)) {
            System.out.println("Link has been shortened!");
        } else {
            System.out.println("Link can't be added!");
        }

        System.out.println();

        System.out.println("Press Enter to Continue...");
        scan.nextLine();
        System.out.println("\n");
    }

}
