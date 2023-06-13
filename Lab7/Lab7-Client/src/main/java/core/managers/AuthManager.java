package core.managers;

import shared.enteties.User;

import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class AuthManager {

    private User user;
    private String type;

    public AuthManager() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Type 'r' to register, 'l' to login: ");
            String input = sc.nextLine().trim().toLowerCase();
            if (!(input.equals("l") || input.equals("r"))) continue;
            System.out.print("Enter your username: ");
            String username = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc.nextLine();
            user = new User(username, password);
            type = input;
            break;
        }
    }

    public User getUser() {
        return this.user;
    }

    public String getType() {
        return this.type;
    }


    private void loginUser(String username, String password) {

    }

    private void registerUser(String username, String password) {

    }
}
