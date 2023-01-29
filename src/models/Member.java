package models;

public class Member extends User {

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "member";
    }
}
